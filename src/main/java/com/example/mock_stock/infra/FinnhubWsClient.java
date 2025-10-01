package com.example.mock_stock.infra;

import com.example.mock_stock.config.FinnhubProperties;
import com.example.mock_stock.domain.Tick;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class FinnhubWsClient {

    private final FinnhubProperties props;
    private final HttpClient http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    private final ObjectMapper om = new ObjectMapper();

    private final Set<String> subscribed = ConcurrentHashMap.newKeySet();
    private final AtomicReference<WebSocket> socketRef = new AtomicReference<>();

    // 핸들러 콜백 인터페이스
    public interface TickHandler { void onTick(Tick tick); }
    private volatile TickHandler handler = tick -> {};

    public FinnhubWsClient(FinnhubProperties props) {
        this.props = props;
        connectWithRetry();
    }

    public void setHandler(TickHandler handler) {
        this.handler = handler;
    }

    /** 종목 구독 */
    public synchronized void subscribe(String symbol) {
        subscribed.add(symbol);
        String msg = String.format("{\"type\":\"subscribe\",\"symbol\":\"%s\"}", symbol);

        send(msg);

        System.out.printf(msg);
    }

    /** 종목 구독 해제 */
    public synchronized void unsubscribe(String symbol) {
        subscribed.remove(symbol);
        String msg = String.format("{\"type\":\"unsubscribe\",\"symbol\":\"%s\"}", symbol);

        send(msg);

        System.out.printf(msg);
    }

    /* ---------------- 내부 구현 ---------------- */

private void connectWithRetry() {
    Executors.newSingleThreadExecutor().submit(() -> {
        int backoff = 1;
        while (true) {
            try {
                connect();
                resubscribeAll();
                return;
            } catch (Exception e) {
                System.err.println("[WS] connect failed: " + e.getMessage());
                // 백오프 대기
                if (!sleepBackoff(backoff)) break; // 인터럽트된 경우 루프 종료
                backoff = Math.min(backoff * 2, 30);
            }
        }
    });
}

/**
 * 백오프 대기 (InterruptedException 처리 포함)
 * @param backoffSeconds 대기할 초
 * @return true: 정상 sleep 후 진행, false: 인터럽트 발생 → 루프 종료 권장
 */
private boolean sleepBackoff(int backoffSeconds) {
    try {
        TimeUnit.SECONDS.sleep(Math.min(backoffSeconds, 30));
        return true;
    } catch (InterruptedException ie) {
        Thread.currentThread().interrupt(); // 인터럽트 상태 복구
        return false;
    }
}

    private void connect() throws Exception {
        var uri = URI.create(props.wsUrl() + "?token=" + props.apiKey());
        var ws = http.newWebSocketBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .buildAsync(uri, new Listener())
                .get(15, TimeUnit.SECONDS);
        socketRef.set(ws);
        System.out.println("[WS] connected " + uri);
    }

    private void resubscribeAll() {
        subscribed.forEach(sym ->
            send(String.format("{\"type\":\"subscribe\",\"symbol\":\"%s\"}", sym))
        );
    }

    private void send(String msg) {
        var ws = socketRef.get();
        if (ws != null) ws.sendText(msg, true);
    }

    private class Listener implements WebSocket.Listener {
        @Override public void onOpen(WebSocket ws) { ws.request(1); }

        @Override
        public CompletionStage<?> onText(WebSocket ws, CharSequence data, boolean last) {
            try {
                JsonNode root = om.readTree(data.toString());
                if ("trade".equals(root.path("type").asText())) {
                    for (JsonNode n : root.path("data")) {
                        Tick tick = new Tick(
                                n.path("s").asText(),
                                n.path("p").asDouble(),
                                n.path("t").asLong(),
                                n.path("v").asLong()
                        );
                        handler.onTick(tick);
                    }
                }
            } catch (Exception e) {
                System.err.println("[WS] parse error: " + e.getMessage());
            } finally {
                ws.request(1);
            }
            return CompletableFuture.completedFuture(null);
        }

        @Override public void onError(WebSocket ws, Throwable error) {
            System.err.println("[WS] error: " + error.getMessage());
            reconnect();
        }

        @Override public CompletionStage<?> onClose(WebSocket ws, int code, String reason) {
            System.err.println("[WS] closed: " + code + " " + reason);
            reconnect();
            return CompletableFuture.completedFuture(null);
        }

        private void reconnect() {
            socketRef.set(null);
            connectWithRetry();
        }
    }
}


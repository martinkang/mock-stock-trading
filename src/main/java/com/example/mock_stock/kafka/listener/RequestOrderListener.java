package com.example.mock_stock.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;

import com.example.mock_stock.domain.model.events.OrderCreatedEvent;
import com.example.mock_stock.domain.model.events.TradeResultEvent;

public class RequestOrderListener {
 @KafkaListener(topics = "orders", groupId = "execution-adapter", concurrency = "6")
    public void orderListener(OrderCreatedEvent e) throws Exception {
        // 1) 시뮬레이터 호출 (동기)
        TradeResultEvent result = http.post().uri("/api/sim/execute")
                .body(e).retrieve().body(TradeResultEvent.class);

        // 2) TODO: DB 반영 (체결/부분체결/거절)
        // orderService.applyTradeResult(result);

        // 3) WebSocket 실시간 브로드캐스트
        ws.broadcast(om.writeValueAsString(result));
    }
}

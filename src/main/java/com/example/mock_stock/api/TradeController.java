package com.example.mock_stock.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.mock_stock.infra.FinnhubWsClient;

@RestController
@RequestMapping("/api/v1/trades")
public class TradeController {

    private final FinnhubWsClient wsClient;

    public TradeController(FinnhubWsClient wsClient) {
        this.wsClient = wsClient;
        // 기본 핸들러: 콘솔에 찍기
        this.wsClient.setHandler(tick ->
            System.out.printf("[TICK] %s p=%.2f v=%d%n",
                    tick.symbol(), tick.price(), tick.volume())
        );
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(@RequestParam String symbol) {
        wsClient.subscribe(symbol.toUpperCase());
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<Void> unsubscribe(@RequestParam String symbol) {
        wsClient.unsubscribe(symbol.toUpperCase());
        return ResponseEntity.accepted().build();
    }
}

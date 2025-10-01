package com.example.mock_stock.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mock_stock.api.common.ApiResponse;
import com.example.mock_stock.domain.dto.QuoteResponse;
import com.example.mock_stock.infra.FinnhubWebClient;

@RestController
@RequestMapping("/api/v1/quote")
public class QuoteController {

    private final FinnhubWebClient finnhubWebClient;

    public QuoteController(FinnhubWebClient finnhubWebClient) {
        this.finnhubWebClient = finnhubWebClient;
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<ApiResponse<QuoteResponse>> getQuote(@PathVariable String symbol) {
        // FinnhubWebClient를 이용하여 심볼에 대한 현재가 정보를 가져옴
        QuoteResponse quote = finnhubWebClient.getQuote(symbol.toUpperCase()).block();
        return ResponseEntity.ok(ApiResponse.success(quote));
    }
}

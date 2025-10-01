package com.example.mock_stock.infra;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.mock_stock.config.FinnhubProperties;
import com.example.mock_stock.domain.dto.QuoteResponse;

import reactor.core.publisher.Mono;


@Component
public class FinnhubWebClient {
    private final WebClient webClient;
    private final FinnhubProperties props;

    public FinnhubWebClient(FinnhubProperties props) {
            this.props = props;
            this.webClient = WebClient.builder()
                    .baseUrl(props.restUrl())
                    .build();
    }

    public Mono<QuoteResponse> getQuote(String symbol) {
    return webClient.get()
            .uri(uriBuilder -> uriBuilder
                    .path("/quote")
                    .queryParam("symbol", symbol)
                    .queryParam("token", props.apiKey())
                    .build())
            .retrieve()
            .bodyToMono(QuoteResponse.class);
    }
}
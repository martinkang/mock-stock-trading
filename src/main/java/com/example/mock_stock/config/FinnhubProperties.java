package com.example.mock_stock.config;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "finnhub")
public record FinnhubProperties(
    String apiKey, 
    String wsUrl, 
    String restUrl
) {}
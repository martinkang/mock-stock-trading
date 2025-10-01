package com.example.mock_stock.domain.dto;

public record Tick(
    String symbol, 
    double price, 
    long tsMillis, 
    long volume
) {}
package com.example.mock_stock.domain;

public record Tick(
    String symbol, 
    double price, 
    long tsMillis, 
    long volume
) {}
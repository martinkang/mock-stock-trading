package com.example.mock_stock.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDtos {
    public record Summary(
        Long id,
        String stockSym,
        String side,
        Integer qty,
        BigDecimal price,
        String status,
        LocalDateTime createdAt
    ) { }
}

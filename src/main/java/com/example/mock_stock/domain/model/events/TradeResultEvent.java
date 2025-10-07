package com.example.mock_stock.domain.model.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.mock_stock.domain.model.enums.TradeStatus;

public record TradeResultEvent(
        Long orderId,
        Long userId,
        String symbol,
        int requestedQuantity,
        int executedQuantity,
        BigDecimal executedPrice,
        TradeStatus status,
        LocalDateTime at
) {}

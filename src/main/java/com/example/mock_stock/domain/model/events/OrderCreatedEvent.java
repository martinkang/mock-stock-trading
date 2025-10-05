package com.example.mock_stock.domain.model.events;

import java.math.BigDecimal;

import com.example.mock_stock.domain.model.enums.SideType;

public record OrderCreatedEvent(
    Long orderId, 
    Long userId, 
    String stockSym, 
    SideType side, 
    Integer qty, 
    BigDecimal price
) {}

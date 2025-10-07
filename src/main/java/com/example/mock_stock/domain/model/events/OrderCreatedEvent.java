package com.example.mock_stock.domain.model.events;

import java.math.BigDecimal;

import com.example.mock_stock.domain.model.entity.Order;
import com.example.mock_stock.domain.model.enums.SideType;

public record OrderCreatedEvent(
    Long orderId, 
    Long userId, 
    String stockSym, 
    SideType side, 
    Integer qty, 
    BigDecimal price
) {
    public static OrderCreatedEvent from(Order order) {
        return new OrderCreatedEvent(
            order.getId(),
            order.getUser().getId(),
            order.getStockSym(),
            order.getSide(),
            order.getQty(),
            order.getPrice()
        );
    }
}

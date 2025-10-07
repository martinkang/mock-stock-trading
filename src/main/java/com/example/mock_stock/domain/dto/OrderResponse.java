package com.example.mock_stock.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.mock_stock.domain.model.entity.Order;
import com.example.mock_stock.domain.model.enums.OrderStatus;
import com.example.mock_stock.domain.model.enums.SideType;

public record OrderResponse(
    Long orderId,          // 주문 번호
    String symbol,        // 주식 심볼 (AAPL 등)
    SideType side,           // BUY / SELL
    OrderStatus status,         // ACCEPTED / FILLED / REJECTED 등
    Integer quantity,      // 주문 수량
    Integer filledQuantity,// 체결된 수량
    BigDecimal price,      // 지정가 or 체결가
    LocalDateTime createdAt     // 주문 시각
) {
        public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getStockSym(),
                order.getSide(),
                order.getStatus(),
                order.getQty(),
                0, // filledQuantity — 아직 체결 전
                order.getPrice(),
                order.getCreatedAt()
        );
    }
}
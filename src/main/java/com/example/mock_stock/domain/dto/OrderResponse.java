package com.example.mock_stock.domain.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

import com.example.mock_stock.domain.model.enums.OrderStatus;
import com.example.mock_stock.domain.model.enums.SideType;

public class OrderResponse {
 private Long orderId;          // 주문 번호
    private String symbol;         // 주식 심볼 (AAPL 등)
    private SideType side;           // BUY / SELL
    private OrderStatus status;         // ACCEPTED / FILLED / REJECTED 등
    private Integer quantity;      // 주문 수량
    private Integer filledQuantity;// 체결된 수량
    private BigDecimal price;      // 지정가 or 체결가
    private LocalDateTime createdAt;     // 주문 시각

    public OrderResponse(Long orderId, String symbol, SideType side, OrderStatus status, Integer quantity, Integer filledQuantity, BigDecimal price, LocalDateTime createdAt) {
        this.orderId = orderId;
        this.symbol = symbol;
        this.side = side;
        this.status = status;
        this.quantity = quantity;
        this.filledQuantity = filledQuantity;
        this.price = price;
        this.createdAt = createdAt;
    }

    // --- getters/setters ---
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public SideType getSide() { return side; }
    public void setSide(SideType side) { this.side = side; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getFilledQuantity() { return filledQuantity; }
    public void setFilledQuantity(Integer filledQuantity) { this.filledQuantity = filledQuantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

package com.example.mock_stock.domain.dto;

import java.math.BigDecimal;

import com.example.mock_stock.domain.model.enums.SideType;

import jakarta.annotation.Nonnull;

public class OrderRequest {

    @Nonnull
    private Long userId;

    @Nonnull
    private String symbol;

    @Nonnull
    private SideType side;   // BUY or SELL

    private Integer quantity;

    private BigDecimal price; // LIMIT일 때만 사용, MARKET은 null

    // --- getters/setters ---
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public SideType getSide() { return side; }
    public void setSide(SideType side) { this.side = side; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}

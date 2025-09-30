package com.example.mock_stock.domain.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "trades")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "trade_qty", nullable = false)
    private Integer tradeQty;

    @Column(name = "trade_price", nullable = false, precision = 18, scale = 2)
    private BigDecimal tradePrice;

    @Column(name = "traded_at", nullable = false)
    private LocalDateTime tradedAt;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    public Integer getTradeQty() { return tradeQty; }
    public void setTradeQty(Integer tradeQty) { this.tradeQty = tradeQty; }
    public BigDecimal getTradePrice() { return tradePrice; }
    public void setTradePrice(BigDecimal tradePrice) { this.tradePrice = tradePrice; }
    public LocalDateTime getTradedAt() { return tradedAt; }
    public void setTradedAt(LocalDateTime tradedAt) { this.tradedAt = tradedAt; }
}

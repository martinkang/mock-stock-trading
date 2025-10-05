package com.example.mock_stock.domain.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "stocks")
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주식 심볼 (예: AAPL, TSLA)
    @Column(nullable = false, unique = true, length = 20)
    private String symbol;

    // 종목 이름 (예: Apple Inc.)
    @Column(nullable = false, length = 100)
    private String name;

    // 현재가
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    // 생성/수정일시 
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // 기본 생성자
    protected StockEntity() {}

    // 편의 생성자
    public StockEntity(String symbol, String name, BigDecimal currentPrice) {
        this.symbol = symbol;
        this.name = name;
        this.price = currentPrice;
    }

    // getter/setter
    public Long getId() { return id; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getCurrentPrice() { return price; }
    public void setCurrentPrice(BigDecimal currentPrice) { this.price = currentPrice; }
}


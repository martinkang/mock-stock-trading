package com.example.mock_stock.domain.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.example.mock_stock.domain.model.enums.OrderStatus;
import com.example.mock_stock.domain.model.enums.SideType;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "stock_sym", nullable = false, length = 12, updatable = false)
    private String stockSym;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 4, updatable = false)
    private SideType side;

    @Column(nullable = false, precision = 18, scale = 2, updatable = false)
    private BigDecimal price;

    @Column(nullable = false, updatable = false)
    private Integer qty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private OrderStatus status = OrderStatus.ACCEPTED;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Version
    private Long version;

    /** 🔒 JPA용 protected 생성자 */
    protected Order() {}

    /** ✅ 필수값만 받는 private 생성자 (Builder에서 호출) */
    private Order(Builder builder) {
        this.user = builder.user;
        this.stockSym = builder.stockSym;
        this.side = builder.side;
        this.price = builder.price;
        this.qty = builder.qty;
        this.createdAt = builder.createdAt != null ? builder.createdAt : LocalDateTime.now();
        this.status = builder.status != null ? builder.status : OrderStatus.ACCEPTED;
    }

    /** ✅ 불변 객체처럼 생성 전 모든 필드 세팅을 보장하는 빌더 */
    public static class Builder {
        private final User user;
        private final String stockSym;
        private final SideType side;
        private final BigDecimal price;
        private final Integer qty;
        private LocalDateTime createdAt;
        private OrderStatus status;

        public Builder(User user, String stockSym, SideType side, BigDecimal price, Integer qty) {
            this.user = Objects.requireNonNull(user, "user must not be null");
            this.stockSym = Objects.requireNonNull(stockSym, "stockSym must not be null");
            this.side = Objects.requireNonNull(side, "side must not be null");
            this.price = Objects.requireNonNull(price, "price must not be null");
            this.qty = Objects.requireNonNull(qty, "qty must not be null");
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    // ✅ getter만 공개 (불변성 유지)
    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getStockSym() { return stockSym; }
    public SideType getSide() { return side; }
    public BigDecimal getPrice() { return price; }
    public Integer getQty() { return qty; }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Long getVersion() { return version; }

    public void changeStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return String.format("Order[id=%d, sym=%s, side=%s, qty=%d, price=%s, status=%s]",
                id, stockSym, side, qty, price, status);
    }
}

package com.example.mock_stock.domain.model.enums;

public enum OrderStatus {
    PENDING,
    ACCEPTED,
    PARTIALLY_FILLED,
    FILLED,
    CANCELLED,
    REJECTED;

    // 체결 가능한 상태 여부
    public boolean isFillable() {
        return this == ACCEPTED || this == PARTIALLY_FILLED;
    }

    // 더 이상 변경 불가능한 상태 여부
    public boolean isFinal() {
        return this == FILLED || this == CANCELLED || this == REJECTED;
    }
}

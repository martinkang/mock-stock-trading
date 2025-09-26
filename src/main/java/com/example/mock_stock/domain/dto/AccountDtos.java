package com.example.mock_stock.domain.dto;

import java.math.BigDecimal;
import java.util.List;

public class AccountDtos {
    public record Summary(
        Long id, 
        String username, 
        BigDecimal balance,
        List<PositionDto> positions
    ) {
        public record PositionDto(String symbol, BigDecimal qty, BigDecimal avgPrice) {}
    }
}

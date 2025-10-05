package com.example.mock_stock.domain.mapper;

import com.example.mock_stock.domain.dto.AccountDtos;
import com.example.mock_stock.domain.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public class AccountDtoMapper {

    public static AccountDtos.Summary toSummary(User user) {
        return new AccountDtos.Summary(
            user.getId(),
            user.getUsername(),
            user.getBalance(),
            List.of()
        );
    }
}

package com.example.mock_stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.mock_stock.domain.dto.AccountDtos;
import com.example.mock_stock.domain.mapper.AccountDtoMapper;
import com.example.mock_stock.domain.model.entity.User;
import com.example.mock_stock.repository.UserRepository;

@Service
@Transactional(readOnly = true)
@RestControllerAdvice
public class AccountQueryService {
    private final UserRepository userRepository;

    public AccountQueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AccountDtos.Summary getAccountSummaryByUserName(String userName) {
        User userEntity = userRepository.findByUsername(userName)
                .orElseThrow(() -> new IllegalArgumentException("해당 username의 사용자를 찾을 수 없습니다: " + userName));
        
        return AccountDtoMapper.toSummary(userEntity);
    }

    public AccountDtos.Summary getAccountSummaryByUserId(Long userId) {
        User userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다: " + userId));
        
        return AccountDtoMapper.toSummary(userEntity);
    }
}

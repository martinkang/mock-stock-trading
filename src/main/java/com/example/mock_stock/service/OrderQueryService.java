package com.example.mock_stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mock_stock.domain.dto.AccountDtos;
import com.example.mock_stock.domain.entity.User;
import com.example.mock_stock.domain.entity.Order;
import com.example.mock_stock.repository.OrderRepository;
import com.example.mock_stock.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderQueryService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderQueryService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }


    public List<Order> findOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> findOrdersByUserIdAndStockSym(Long userId, String stockSym) {
        return orderRepository.findByUserIdAndStockSym(userId, stockSym);
    }
}

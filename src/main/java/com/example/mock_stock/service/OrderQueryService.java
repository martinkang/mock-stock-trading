package com.example.mock_stock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mock_stock.domain.dto.OrderDtos.Detail;
import com.example.mock_stock.domain.dto.OrderDtos.Summary;
import com.example.mock_stock.domain.model.entity.Order;
import com.example.mock_stock.domain.model.entity.User;
import com.example.mock_stock.repository.OrderRepository;
import com.example.mock_stock.repository.UserRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderQueryService {
    private final OrderRepository orderRepository;

    public OrderQueryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // public List<Summary> getOrdersByUserId(Long userId) {
    //     List<Order> orders = orderRepository.findByUserId(userId);

    //     return orders.stream()
    //         .map(OrderDtoMapper::toSummary)
    //         .toList();
    // }

    // public List<Summary> getOrdersByUserIdAndStockSym(Long userId, String stockSym) {
    //     List<Order> orders = orderRepository.findByUserIdAndStockSym(userId, stockSym);

    //     return orders.stream()
    //         .map(OrderDtoMapper::toSummary)
    //         .toList();
    // }

    // public Detail getOrderDetailById(Long orderId) {
    //     Order order = orderRepository.findById(orderId)
    //             .orElseThrow(() -> new IllegalArgumentException("해당 ID의 주문을 찾을 수 없습니다: " + orderId));

    //     return OrderDtoMapper.toDetail(order);
    // }
}

package com.example.mock_stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mock_stock.domain.model.entity.Order;
import com.example.mock_stock.domain.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByUser(User user);
    List<Order> findByUserIdAndStockSym(Long userId, String stockSym);
    Optional<Order> findById(Long orderId);
}

package com.example.mock_stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mock_stock.domain.model.entity.Order;
import com.example.mock_stock.domain.model.entity.Trade;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findByOrderId(Long orderId);
    List<Trade> findByOrder(Order order);
}

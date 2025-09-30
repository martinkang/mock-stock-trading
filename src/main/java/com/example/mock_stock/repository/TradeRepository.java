package com.example.mock_stock.repository;

import com.example.mock_stock.domain.entity.Trade;
import com.example.mock_stock.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    List<Trade> findByOrderId(Long orderId);
    List<Trade> findByOrder(Order order);
}

package com.example.mock_stock.repository;

import com.example.mock_stock.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // ...custom query methods if needed...
}

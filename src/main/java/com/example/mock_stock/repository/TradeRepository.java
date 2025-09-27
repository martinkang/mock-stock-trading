package com.example.mock_stock.repository;

import com.example.mock_stock.domain.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
    // ...custom query methods if needed...
}

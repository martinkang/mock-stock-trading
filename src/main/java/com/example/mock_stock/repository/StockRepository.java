package com.example.mock_stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mock_stock.domain.model.entity.StockEntity;

import java.util.Optional;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findById(Long id);
    Optional<StockEntity> findBySymbol(String symbol);
}

package com.example.mock_stock.domain.mapper;

import com.example.mock_stock.domain.dto.OrderDtos;
import com.example.mock_stock.domain.entity.Order;
import com.example.mock_stock.domain.entity.Trade;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDtoMapper {
    public static OrderDtos.Summary toSummary(Order order) {
        return new OrderDtos.Summary(
            order.getId(),
            order.getStockSym(),
            order.getSide(),
            order.getQty(),
            order.getPrice(),
            order.getStatus(),
            order.getCreatedAt()
        );
    }

    public static OrderDtos.Detail toDetail(Order order) {
  
        return new OrderDtos.Detail(
            order.getId(),
            order.getStockSym(),
            order.getSide(),
            order.getQty(),
            order.getPrice(),
            order.getStatus(),
            order.getCreatedAt()
        );
    }
}

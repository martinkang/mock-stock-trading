package com.example.mock_stock.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.mock_stock.api.common.*;
import com.example.mock_stock.domain.dto.OrderDtos;
import com.example.mock_stock.domain.entity.Order;
import com.example.mock_stock.domain.dto.OrderDtos.Detail;
import com.example.mock_stock.domain.dto.OrderDtos.Summary;
import com.example.mock_stock.service.OrderQueryService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderQueryService orderQueryService;
    
    public OrderController(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Summary>>> orders(
        @RequestParam("userId") Long userId
    ) {
        List<Summary> summary = orderQueryService.getOrdersByUserId(userId);

        return ResponseEntity.ok(ApiResponse.success(summary));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<Detail>> orderDetail(
        @PathVariable Long orderId
    ) {
        Detail detail = orderQueryService.getOrderDetailById(orderId);

        return ResponseEntity.ok(ApiResponse.success(detail));
    }
}

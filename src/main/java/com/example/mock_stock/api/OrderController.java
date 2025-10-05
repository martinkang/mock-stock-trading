package com.example.mock_stock.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mock_stock.api.common.ApiResponse;
import com.example.mock_stock.domain.dto.OrderRequest;
import com.example.mock_stock.domain.dto.OrderResponse;
import com.example.mock_stock.service.OrderMutationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderMutationService orderMutationService;

    public OrderController(OrderMutationService orderMutationService) {
        this.orderMutationService = orderMutationService;
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId) {
        return new String();
    }
    
    @GetMapping()
    public String getUserOrders(@RequestParam Long userId, @RequestParam(required = false) String status) {
        return new String();
    }

    @PostMapping("/{orderId}/cancel")
    public String cancleORder(@PathVariable Long orderId) {
        //TODO: process POST request
        
        return new String();
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<OrderResponse>> order(@RequestBody OrderRequest request) {

        OrderResponse orderResponse = orderMutationService.order(request);
        
        return ResponseEntity.ok(ApiResponse.success(orderResponse));
    }
    
    
}

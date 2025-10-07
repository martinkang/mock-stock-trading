package com.example.mock_stock.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mock_stock.domain.dto.OrderRequest;
import com.example.mock_stock.domain.dto.OrderResponse;
import com.example.mock_stock.domain.model.entity.Order;
import com.example.mock_stock.domain.model.entity.StockEntity;
import com.example.mock_stock.domain.model.entity.User;
import com.example.mock_stock.domain.model.enums.OrderStatus;
import com.example.mock_stock.domain.model.enums.SideType;
import com.example.mock_stock.domain.model.events.OrderCreatedEvent;
import com.example.mock_stock.repository.OrderRepository;
import com.example.mock_stock.repository.StockRepository;
import com.example.mock_stock.repository.UserRepository;

@Service
@Transactional
public class OrderMutationService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final ApplicationEventPublisher publisher;

    public OrderMutationService(
        OrderRepository orderRepository, 
        UserRepository userRepository,
        StockRepository stockRepository,
        ApplicationEventPublisher publisher
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.stockRepository = stockRepository;
        this.publisher = publisher;
    }

    public OrderResponse order(OrderRequest request) {
        validation(request);

        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다: " + request.getUserId()));

        StockEntity stock = stockRepository.findBySymbol(request.getSymbol())
            .orElseThrow(() -> new IllegalArgumentException("해당 심볼의 주식을 찾을 수 없습니다: " + request.getSymbol()));

        BigDecimal balance = user.getBalance();
        BigDecimal totalPrice = request.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()));

        if (request.getSide() == SideType.BUY) {
            // BUY 주문인 경우 잔액 체크
            if (balance.compareTo(totalPrice) < 0) {
                throw new IllegalArgumentException("잔액이 부족합니다. 현재 잔액: " + balance + ", 주문 총액: " + totalPrice);
            }

             user.setBalance(balance.subtract(totalPrice));
        } 
            
        Order newOrder = new Order.Builder(
                user,                   
                request.getSymbol(),     
                request.getSide(),      
                request.getPrice(),      
                request.getQuantity()   
        )
        .status(OrderStatus.ACCEPTED)  
        .createdAt(LocalDateTime.now()) 
        .build();

        Order savedOrder = orderRepository.save(newOrder);

        publisher.publishEvent(OrderCreatedEvent.from(savedOrder));

        System.out.println("Order " + savedOrder.toString() + " user : " + user.toString() + " placed successfully." );

        return OrderResponse.from(savedOrder);
    }

    private void validation(OrderRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        Objects.requireNonNull(request.getUserId(), "userId must not be null");
        Objects.requireNonNull(request.getSymbol(), "symbol must not be null");
        Objects.requireNonNull(request.getSide(), "side must not be null");
        Objects.requireNonNull(request.getPrice(), "price must not be null");
        Objects.requireNonNull(request.getQuantity(), "quantity must not be null");
    }
}

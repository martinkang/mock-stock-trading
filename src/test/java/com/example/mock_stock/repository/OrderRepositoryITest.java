package com.example.mock_stock.repository;

import com.example.mock_stock.domain.entity.User;
import com.example.mock_stock.domain.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryITest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void save_and_find_order() {
        User user = new User();
        user.setUsername("order_integration");
        user.setBalance(new BigDecimal("5000"));
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);

        Order order = new Order();
        order.setUser(user);
        order.setStockSym("MSFT");
        order.setSide("BUY");
        order.setQty(20);
        order.setPrice(new BigDecimal("300.00"));
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        assertThat(savedOrder.getId()).isNotNull();
        assertThat(orderRepository.findById(savedOrder.getId())).isPresent();
    }

    @Test
    void save_and_find_by_userId() {
        User user = new User();
        user.setUsername("order_integration");
        user.setBalance(new BigDecimal("5000"));
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);

        Order order = new Order();
        order.setUser(user);
        order.setStockSym("MSFT");
        order.setSide("BUY");
        order.setQty(20);
        order.setPrice(new BigDecimal("300.00"));
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        order = orderRepository.save(order);

        List<Order> ordersByUserId = orderRepository.findByUserId(user.getId());
        assertThat(ordersByUserId).isNotEmpty();
        assertThat(ordersByUserId.get(0).getUser().getId()).isEqualTo(user.getId());
    }
}

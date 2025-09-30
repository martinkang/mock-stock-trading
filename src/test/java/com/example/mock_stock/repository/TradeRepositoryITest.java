package com.example.mock_stock.repository;

import com.example.mock_stock.domain.entity.User;
import com.example.mock_stock.domain.entity.Order;
import com.example.mock_stock.domain.entity.Trade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TradeRepositoryITest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    TradeRepository tradeRepository;

    @Test
    void save_and_find_trade() {
        User user = new User();
        user.setUsername("trade_integration");
        user.setBalance(new BigDecimal("3000"));
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);

        Order order = new Order();
        order.setUser(user);
        order.setStockSym("TSLA");
        order.setSide("SELL");
        order.setQty(15);
        order.setPrice(new BigDecimal("700.00"));
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        order = orderRepository.save(order);

        Trade trade = new Trade();
        trade.setOrder(order);
        trade.setTradeQty(15);
        trade.setTradePrice(new BigDecimal("700.00"));
        trade.setTradedAt(LocalDateTime.now());
        Trade savedTrade = tradeRepository.save(trade);

        assertThat(savedTrade.getId()).isNotNull();
        assertThat(tradeRepository.findById(savedTrade.getId())).isPresent();
    }

    @Test
    void save_and_find_by_orderId() {
        User user = new User();
        user.setUsername("trade_integration");
        user.setBalance(new BigDecimal("3000"));
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);

        Order order = new Order();
        order.setUser(user);
        order.setStockSym("TSLA");
        order.setSide("SELL");
        order.setQty(15);
        order.setPrice(new BigDecimal("700.00"));
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        order = orderRepository.save(order);

        Trade trade = new Trade();
        trade.setOrder(order);
        trade.setTradeQty(15);
        trade.setTradePrice(new BigDecimal("700.00"));
        trade.setTradedAt(LocalDateTime.now());
        trade = tradeRepository.save(trade);

        List<Trade> tradesByOrderId = tradeRepository.findByOrderId(order.getId());
        assertThat(tradesByOrderId).isNotEmpty();
        assertThat(tradesByOrderId.get(0).getOrder().getId()).isEqualTo(order.getId());
    }
}

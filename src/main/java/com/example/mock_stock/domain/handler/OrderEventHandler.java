package com.example.mock_stock.domain.handler;

import org.aspectj.weaver.ast.Or;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;

import com.example.mock_stock.domain.model.events.OrderCreatedEvent;

@Component
public class OrderEventHandler {

    public OrderEventHandler() {}

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void orderCreatedHandler(OrderCreatedEvent event) {
        System.out.println("Order Created Event Handled: " + event);
    }
}

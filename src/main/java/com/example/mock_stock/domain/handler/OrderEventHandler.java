package com.example.mock_stock.domain.handler;

import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;

import com.example.mock_stock.domain.model.events.OrderCreatedEvent;

@Component
public class OrderEventHandler {
    private final KafkaTemplate<String, Object> kafka;
    private final String ORDER_TOPIC = "orders";

    public OrderEventHandler(KafkaTemplate<String, Object> kafka) {
        this.kafka = kafka;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void orderCreatedHandler(OrderCreatedEvent event) {
        System.out.println("Order Created Event Handled: " + event);

        kafka.send(ORDER_TOPIC, event.stockSym(), event);
    }
}

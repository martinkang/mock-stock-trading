package com.example.mock_stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.apache.kafka.clients.admin.NewTopic;

@Configuration
public class KafkaConfig {
    @Bean public NewTopic ordersTopic()     { return TopicBuilder.name("orders").partitions(12).replicas(1).build(); }
    @Bean public NewTopic executionsTopic() { return TopicBuilder.name("executions").partitions(12).replicas(1).build(); }
}

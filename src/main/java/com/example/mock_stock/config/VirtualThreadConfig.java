package com.example.mock_stock.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mock_stock.executor.PerKeySerialExecutors;

@Configuration
public class VirtualThreadConfig {
  /** 가상 스레드 per-task 실행기 (JDK 21) */
  @Bean(destroyMethod = "close")
  ExecutorService vtExecutor() {
    return Executors.newVirtualThreadPerTaskExecutor();
  }

  /** 심볼별 직렬 실행기 레지스트리 */
  @Bean
  PerKeySerialExecutors perKeyExecutors(ExecutorService vtExecutor) {
    return new PerKeySerialExecutors(vtExecutor);
  }
}

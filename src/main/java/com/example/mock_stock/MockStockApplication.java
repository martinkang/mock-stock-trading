package com.example.mock_stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.mock_stock.config.FinnhubProperties;

@SpringBootApplication
@EnableConfigurationProperties(FinnhubProperties.class)
public class MockStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockStockApplication.class, args);
	}

}

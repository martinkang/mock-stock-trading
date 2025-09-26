package com.example.mock_stock.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mock_stock.api.common.*;
import com.example.mock_stock.domain.dto.AccountDtos;
import com.example.mock_stock.domain.dto.AccountDtos.*;
import com.example.mock_stock.domain.dto.AccountDtos.Summary.PositionDto;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Summary>> getAccountSummary(@RequestHeader("X-User-Name") String userName) {
        Summary summary = new Summary(
            1L,
            "mockuser",
            new java.math.BigDecimal("10000.00"),
            java.util.List.of(
                new PositionDto("AAPL", new java.math.BigDecimal("50"), new java.math.BigDecimal("150.00")),
                new PositionDto("TSLA", new java.math.BigDecimal("20"), new java.math.BigDecimal("600.00"))
            )
        );
        return ResponseEntity.ok(ApiResponse.success(summary));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<ApiResponse<Summary>> getAccountSummaryTest(@PathVariable Long accountId) {
        Summary summary = new Summary(
            accountId,
            "mockuser",
            new java.math.BigDecimal("10000.00"),
            java.util.List.of(
                new PositionDto("AAPL", new java.math.BigDecimal("50"), new java.math.BigDecimal("150.00")),
                new PositionDto("TSLA", new java.math.BigDecimal("20"), new java.math.BigDecimal("600.00"))
            )
        );
        return ResponseEntity.ok(ApiResponse.success(summary));
    }
}

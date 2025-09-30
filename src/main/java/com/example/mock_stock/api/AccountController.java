package com.example.mock_stock.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mock_stock.api.common.*;
import com.example.mock_stock.domain.dto.AccountDtos.*;
import com.example.mock_stock.service.AccountQueryService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountQueryService accountQueryService;
    
    public AccountController(AccountQueryService accountQueryService) {
        this.accountQueryService = accountQueryService;
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Summary>> getAccountSummary(
        @RequestHeader("X-User-Name") String userName
) {
        Summary summary = accountQueryService.getAccountSummaryByUserName(userName);
        return ResponseEntity.ok(ApiResponse.success(summary));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<ApiResponse<Summary>> getAccountSummaryTest(@PathVariable Long accountId) {
        Summary summary = accountQueryService.getAccountSummaryByUserId(accountId);

        return ResponseEntity.ok(ApiResponse.success(summary));
    }
}

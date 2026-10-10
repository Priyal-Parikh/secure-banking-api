package com.example.banking.controller;

import com.example.banking.model.Account;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable UUID id, @AuthenticationPrincipal Jwt jwt) {
        Account mockAccount = Account.builder()
                .id(id)
                .accountHolderName("Sample Enterprise Account")
                .balance(new BigDecimal("12500.50"))
                .currency("GBP")
                .build();

        return ResponseEntity.ok(mockAccount);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        account.setId(UUID.randomUUID());
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }
}

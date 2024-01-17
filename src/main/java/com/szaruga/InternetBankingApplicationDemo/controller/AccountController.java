package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.GetAccountsByIdDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountsPageDto;
import com.szaruga.InternetBankingApplicationDemo.model.account.CreateAccount;
import com.szaruga.InternetBankingApplicationDemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/users/accounts/{pageNumber}/{pageSize}")
    public ResponseEntity<Map<String, Object>> retrievePageOfAccountsWithoutSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize) {
        Page<AccountsPageDto> accountsPage = accountService.getAllAccounts(pageNumber, pageSize, null);
        Map<String, Object> response = new HashMap<>();
        response.put("accounts", accountsPage.getContent());
        response.put("currentPage", accountsPage.getNumber());
        response.put("totalItems", accountsPage.getTotalElements());
        response.put("totalPages", accountsPage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/accounts/{pageNumber}/{pageSize}/{sort}")
    public ResponseEntity<Map<String, Object>> retrievePageOfAccountsWithSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize,
            @PathVariable String sort) {
        Page<AccountsPageDto> accountsPage = accountService.getAllAccounts(pageNumber, pageSize, sort);
        Map<String, Object> response = new HashMap<>();
        response.put("accounts", accountsPage.getContent());
        response.put("currentPage", accountsPage.getNumber());
        response.put("totalItems", accountsPage.getTotalElements());
        response.put("totalPages", accountsPage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/accounts/{id}")
    public ResponseEntity<GetAccountsByIdDto> getAccountsById(
            @PathVariable int id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping("/users/{userId}/accounts")
    public ResponseEntity<CreateAccount> createAccount(
            @RequestBody AccountDto accountDto,
            @PathVariable long userId) {
        return ResponseEntity.ok(accountService.saveAccount(accountDto, userId));
    }

    @DeleteMapping("/users/account/{id}")
    public void deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
    }

    @PutMapping("/users/{userId}/accounts/{accountId}/deposit/{amount}")
    public ResponseEntity<Void> depositMoneyOnAccount(
            @PathVariable long userId,
            @PathVariable int accountId,
            @PathVariable BigDecimal amount
    ) {
        accountService.depositMoney(userId, accountId, amount);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{userId}/accounts/{accountId}/withdraw/{amount}")
    public ResponseEntity<Void> withdrawMoneyOnAccount(
            @PathVariable long userId,
            @PathVariable int accountId,
            @PathVariable BigDecimal amount
    ) {
        accountService.withdrawMoney(userId, accountId, amount);
        return ResponseEntity.ok().build();
    }
}
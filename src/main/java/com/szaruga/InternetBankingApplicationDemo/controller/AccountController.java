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

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/users/accounts/{pageNumber}/{pageSize}")
    public List<AccountsPageDto> retrievePageOfAccountsWithoutSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize) {
        Page<AccountsPageDto> data = accountService.getAccountsPagination(pageNumber, pageSize, null);
        return data.getContent();
    }

    @GetMapping("/users/accounts/{pageNumber}/{pageSize}/{sort}")
    public List<AccountsPageDto> retrievePageOfAccountsWithSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize,
            @PathVariable String sort) {
        Page<AccountsPageDto> data = accountService.getAccountsPagination(pageNumber, pageSize, sort);
        return data.getContent();
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
}

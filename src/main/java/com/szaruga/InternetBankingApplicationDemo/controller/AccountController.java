package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.dto.account.GetAccountsByIdDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountsPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;
import com.szaruga.InternetBankingApplicationDemo.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/user/accounts/{pageNumber}/{pageSize}")
    public List<AccountsPageDto> retrievePageOfAccountsWithoutSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize) {
        Page<AccountsPageDto> data = accountService.getAccountsPagination(pageNumber, pageSize, null);
        return data.getContent();
    }

    @GetMapping("/user/accounts/{pageNumber}/{pageSize}/{sort}")
    public List<AccountsPageDto> retrievePageOfAccountsWithSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize,
            @PathVariable String sort) {
        Page<AccountsPageDto> data = accountService.getAccountsPagination(pageNumber, pageSize, sort);
        return data.getContent();
    }

    @GetMapping("/user/account/{id}")
    public ResponseEntity<GetAccountsByIdDto> getAccountsById(@PathVariable int id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping("/user/accountEntity")
    public ResponseEntity<AccountEntity> createAccount(@Valid @RequestBody AccountEntity accountEntity) {
        AccountEntity savedAccountEntity = accountService.saveAccount(accountEntity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAccountEntity)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/user/account/{id}")
    public void deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
    }
}

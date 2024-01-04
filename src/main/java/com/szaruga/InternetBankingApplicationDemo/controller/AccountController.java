package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.account.AccountNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.ACCOUNT_NOT_FOUND_WITH_ID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/user/accounts")
    public List<AccountEntity> retrieveAllAccounts() {
        return accountService.findAllAccounts();
    }

    @GetMapping("/user/account/{id}")
    public EntityModel<AccountEntity> retrieveAccountById(@PathVariable int id) {
        AccountEntity AccountEntity = accountService.findAccountById(id);
        if (AccountEntity == null) {
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_WITH_ID.getMessage() + id);
        }
        EntityModel<AccountEntity> entityModel = EntityModel.of(AccountEntity);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllAccounts());
        entityModel.add(link.withRel("all-accounts"));
        return entityModel;
    }

    @PostMapping("/user/accountEntity")
    public ResponseEntity<AccountEntity> createAccount(@Valid @RequestBody AccountEntity accountEntity) {
        AccountEntity savedAccountEntity = accountService.saveAccount(accountEntity);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAccountEntity)
                .toUri();
        return ResponseEntity.created(location).build();
        //TODO ask master how to set up user_id when POST Json file
    }

    @DeleteMapping("/user/account/{id}")
    public void deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
    }
}

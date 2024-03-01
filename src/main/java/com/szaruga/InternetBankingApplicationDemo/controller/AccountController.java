package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.GetAccountsByIdDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountsPageDto;
import com.szaruga.InternetBankingApplicationDemo.model.account.CreateAccount;
import com.szaruga.InternetBankingApplicationDemo.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for managing account-related operations.
 */
@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountsService accountsService;

    /**
     * Constructs an instance of the AccountController.
     *
     * @param accountsService The service for managing account operations.
     */
    @Autowired
    public AccountController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    /**
     * Helper method to structure the response for paginated account data.
     *
     * @param page The paginated account data.
     * @return A structured response.
     */
    private Object responsePage(Page<AccountsPageDto> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("accounts", page.getContent());
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return response;
    }

    /**
     * Retrieves a paginated list of accounts without sorting.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The size of each page.
     * @return ResponseEntity containing the paginated account data.
     */
    @GetMapping("/users/accounts/{pageNumber}/{pageSize}")
    public ResponseEntity<Object> retrievePageOfAccountsWithoutSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize) {
        Page<AccountsPageDto> accountsPage = accountsService.getAllAccounts(pageNumber, pageSize, null);
        return ResponseEntity.ok(responsePage(accountsPage));
    }

    /**
     * Retrieves a paginated list of accounts with sorting.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The size of each page.
     * @param sort       The field to sort by.
     * @return ResponseEntity containing the paginated and sorted account data.
     */
    @GetMapping("/users/accounts/{pageNumber}/{pageSize}/{sort}")
    public ResponseEntity<Object> retrievePageOfAccountsWithSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize,
            @PathVariable String sort) {
        Page<AccountsPageDto> accountsPage = accountsService.getAllAccounts(pageNumber, pageSize, sort);
        return ResponseEntity.ok(responsePage(accountsPage));
    }

    /**
     * Retrieves account details by account ID.
     *
     * @param id The ID of the account.
     * @return ResponseEntity containing the account details.
     */
    @GetMapping("/users/accounts/{id}")
    public ResponseEntity<GetAccountsByIdDto> getAccountsById(
            @PathVariable int id) {
        return ResponseEntity.ok(accountsService.getAccountById(id));
    }

    /**
     * Creates a new account for a user.
     *
     * @param accountDto The account details.
     * @param userId     The ID of the user.
     * @return ResponseEntity containing the details of the created account.
     */
    @PostMapping("/users/{userId}/accounts")
    public ResponseEntity<CreateAccount> createAccount(
            @RequestBody AccountDto accountDto,
            @PathVariable long userId) {
        return ResponseEntity.ok(accountsService.saveAccount(accountDto, userId));
    }

    /**
     * Deletes an account by ID.
     *
     * @param id The ID of the account to delete.
     */
    @DeleteMapping("/users/account/{id}")
    public void deleteAccount(@PathVariable int id) {
        accountsService.deleteAccount(id);
    }

    /**
     * Deposits money into an account.
     *
     * @param userId    The ID of the user.
     * @param accountId The ID of the account.
     * @param amount    The amount to deposit.
     * @return ResponseEntity indicating success.
     */
    @PutMapping("/users/{userId}/accounts/{accountId}/deposit/{amount}")
    public ResponseEntity<Void> depositMoneyOnAccount(
            @PathVariable long userId,
            @PathVariable int accountId,
            @PathVariable BigDecimal amount
    ) {
        accountsService.depositMoney(userId, accountId, amount);
        return ResponseEntity.ok().build();
    }

    /**
     * Withdraws money from an account.
     *
     * @param userId    The ID of the user.
     * @param accountId The ID of the account.
     * @param amount    The amount to withdraw.
     * @return ResponseEntity indicating success.
     */
    @PutMapping("/users/{userId}/accounts/{accountId}/withdraw/{amount}")
    public ResponseEntity<Void> withdrawMoneyOnAccount(
            @PathVariable long userId,
            @PathVariable int accountId,
            @PathVariable BigDecimal amount
    ) {
        accountsService.withdrawMoney(userId, accountId, amount);
        return ResponseEntity.ok().build();
    }
}
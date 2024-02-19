package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.GetAccountsByIdDto;
import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountsPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.account.InsufficientBalanceException;
import com.szaruga.InternetBankingApplicationDemo.exception.account.AccountNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.IllegalSortingRequest;
import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.mapper.AccountMapper;
import com.szaruga.InternetBankingApplicationDemo.mapper.UserDetailsMapper;
import com.szaruga.InternetBankingApplicationDemo.model.account.CreateAccount;
import com.szaruga.InternetBankingApplicationDemo.util.AccountUtils;
import com.szaruga.InternetBankingApplicationDemo.util.SortingStringValues;
import com.szaruga.InternetBankingApplicationDemo.verification.accountdto.ValidationAccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
/**
 * Service class for managing accounts.
 */
@Service
public class AccountService {
    /**
     * Constructor for AccountService.
     *
     * @param accountRepository    The repository for AccountEntity.
     * @param accountUtils         Utility class for account-related operations.
     * @param validationAccountDto Utility class for validating AccountDto objects.
     * @param userRepository       The repository for UserEntity.
     */
    private final AccountRepository accountRepository;
    private final AccountUtils accountUtils;
    private final ValidationAccountDto validationAccountDto;
    private final UserRepository userRepository;
    /**
     * Constructor for AccountService.
     *
     * @param accountRepository    The repository for AccountEntity.
     * @param accountUtils         Utility class for account-related operations.
     * @param validationAccountDto Utility class for validating AccountDto objects.
     * @param userRepository       The repository for UserEntity.
     */
    @Autowired
    public AccountService(
            AccountRepository accountRepository,
            AccountUtils accountUtils,
            ValidationAccountDto validationAccountDto,
            UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.accountUtils = accountUtils;
        this.validationAccountDto = validationAccountDto;
        this.userRepository = userRepository;
    }
    /**
     * Retrieves a page of accounts.
     *
     * @param pageNumber  The page number to retrieve.
     * @param pageSize    The size of each page.
     * @param sortByInput The field to sort by.
     * @return A page of accounts.
     * @throws IllegalSortingRequest If the sorting request is invalid.
     */
    public Page<AccountsPageDto> getAllAccounts(int pageNumber, int pageSize, String sortByInput) {
        Pageable pageable;
        if (sortByInput == null) {
            pageable = PageRequest.of(pageNumber, pageSize);
            return accountRepository.findAll(pageable).map(AccountMapper::mapAccountsEntityToPageDto);
        } else {
            Sort sort = Sort.by(Sort.Direction.ASC, sortByInput);
            pageable = PageRequest.of(pageNumber, pageSize, sort);
            for (String sortMessage : SortingStringValues.sortingMessages) {
                {
                    if (sortMessage.equalsIgnoreCase(sortByInput)) {
                        return accountRepository.findAll(pageable).map(AccountMapper::mapAccountsEntityToPageDto);                    }
                }
            }
        }
        throw new IllegalSortingRequest(INVALID_SORT_FIELD.getMessage() + sortByInput);
    }
    /**
     * Retrieves an account by its ID.
     *
     * @param accountId The ID of the account to retrieve.
     * @return The account with the specified ID.
     * @throws AccountNotFoundException If no account with the specified ID is found.
     */
    public GetAccountsByIdDto getAccountById(int accountId) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_WITH_ID.getMessage() + accountId));
        return AccountMapper.mapAccountEntityToGetAccountsByIdDto(account);
    }
    /**
     * Saves an account.
     *
     * @param accountDto The account to save.
     * @param userId     The ID of the user associated with the account.
     * @return Information about the created account.
     * @throws UserNotFoundException    If no user with the specified ID is found.
     * @throws InsufficientBalanceException If the account balance is insufficient.
     */
    public CreateAccount saveAccount(AccountDto accountDto, long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + userId));
        validationAccountDto.validateDto(accountDto);
        accountDto.setBalance(BigDecimal.ZERO);
        accountDto.setReferenceAccountNumber(accountUtils.generateReferenceAccountNumber());
        accountDto.setUser(userEntity);
        AccountEntity save = accountRepository.save(AccountMapper.toEntity(accountDto));
        return new CreateAccount(save.getId());
    }
    /**
     * Deletes an account by its ID.
     *
     * @param accountId The ID of the account to delete.
     * @throws AccountNotFoundException If no account with the specified ID is found.
     */
    public void deleteAccount(int accountId) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_WITH_ID.getMessage() + accountId));
        if (account.getBalance().equals(BigDecimal.ZERO)) {
            accountRepository.delete(account);
        } else {
            throw new AccountNotFoundException(ACCOUNT_DELETE.getMessage());
        }
    }
    /**
     * Deposits money into an account.
     *
     * @param userId    The ID of the user associated with the account.
     * @param accountId The ID of the account to deposit money into.
     * @param amount    The amount of money to deposit.
     * @throws UserNotFoundException    If no user with the specified ID is found.
     * @throws AccountNotFoundException If no account with the specified ID is found.
     */
    public void depositMoney(long userId, int accountId, BigDecimal amount) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + userId));
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_WITH_ID.getMessage() + accountId));
        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.add(amount);
        account.setUser(userEntity);
        account.setBalance(newBalance);
        accountRepository.save(account);
    }
    /**
     * Withdraws money from an account.
     *
     * @param userId    The ID of the user associated with the account.
     * @param accountId The ID of the account to withdraw money from.
     * @param amount    The amount of money to withdraw.
     * @throws UserNotFoundException       If no user with the specified ID is found.
     * @throws AccountNotFoundException    If no account with the specified ID is found.
     * @throws InsufficientBalanceException If the account balance is insufficient for the withdrawal.
     */
    public void withdrawMoney(long userId, int accountId, BigDecimal amount) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + userId));
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_WITH_ID.getMessage() + accountId));
        BigDecimal currentBalance = account.getBalance();
        if (currentBalance.compareTo(amount) <= 0) {
            throw new InsufficientBalanceException(INSUFFICIENT_BALANCE.getMessage());
        }
        BigDecimal newBalance = currentBalance.subtract(amount);
        account.setUser(userEntity);
        account.setBalance(newBalance);
        accountRepository.save(account);
    }
}
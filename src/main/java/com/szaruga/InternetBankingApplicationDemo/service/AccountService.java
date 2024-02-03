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

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountUtils accountUtils;
    private final ValidationAccountDto validationAccountDto;
    private final UserRepository userRepository;

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

    public GetAccountsByIdDto getAccountById(int accountId) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_WITH_ID.getMessage() + accountId));
        return AccountMapper.mapAccountEntityToGetAccountsByIdDto(account);
    }

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

    public void deleteAccount(int accountId) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_WITH_ID.getMessage() + accountId));
        if (account.getBalance().equals(BigDecimal.ZERO)) {
            accountRepository.delete(account);
        } else {
            throw new AccountNotFoundException(ACCOUNT_DELETE.getMessage());
        }
    }

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
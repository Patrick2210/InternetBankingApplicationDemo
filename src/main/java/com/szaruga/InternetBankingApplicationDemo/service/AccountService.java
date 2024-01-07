package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.account.AccountNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import com.szaruga.InternetBankingApplicationDemo.util.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.ACCOUNT_NOT_FOUND_WITH_ID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountUtils accountUtils;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountUtils accountUtils) {
        this.accountRepository = accountRepository;
        this.accountUtils = accountUtils;
    }

    public List<AccountEntity> findAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountEntity findAccountById(int id) {
        Optional<AccountEntity> optionalAccount = accountRepository.findById(id);
        Predicate<? super AccountEntity> predicate = accountEntity -> accountEntity.getId().equals(id);
        return optionalAccount.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public AccountEntity saveAccount(AccountEntity accountEntity) {
        accountEntity.setBalance(0.0f); //todo wyjebac float na BigDecimal i nie robic takich operacji na float
        accountEntity.setReferenceAccountNumber(accountUtils.generateReferenceAccountNumber());
        return accountRepository.save(accountEntity);
    }

    public void deleteAccount(int id) {
        Optional<AccountEntity> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            accountRepository.deleteById(id);
        } else {
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_WITH_ID.getMessage() + id);
        }
    }
}

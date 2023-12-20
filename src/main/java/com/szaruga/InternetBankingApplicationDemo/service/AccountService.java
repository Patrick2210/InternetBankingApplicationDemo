package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.entity.Account;
import com.szaruga.InternetBankingApplicationDemo.exception.account.AccountNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.ACCOUNT_NOT_FOUND_WITH_ID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    public Account findAccountById(int id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        Predicate<? super Account> predicate = account -> account.getId().equals(id);
        return optionalAccount.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public Account saveAccount(Account account) {
        account.setBalance(0.0f);
        account.setReferenceAccountNumber(generateReferenceAccountNumber());
        return accountRepository.save(account);
    }

    public void deleteAccount(int id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            accountRepository.deleteById(id);
        } else throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_WITH_ID.getMessage() + id);
    }

    private boolean isAccountReferenceAccountExists(int referenceAccountNumber) {
        return accountRepository.existsByReferenceAccountNumber(referenceAccountNumber);
    }

    private int generateRandomNumber() {
        int min = 100000;
        int max = 999999;
        return min + new Random().nextInt(max - min + 1);
    }

    private int generateReferenceAccountNumber() {
        int randomReferenceAccountNumber = generateRandomNumber();
        int anotherAttemptCreatingReferenceAccountNumber = 0;
        if (!isAccountReferenceAccountExists(randomReferenceAccountNumber)) {
            return randomReferenceAccountNumber;
        } else {
            int maxAttempt = 10;
            for (int i = 0; i <= maxAttempt; i++) {
                anotherAttemptCreatingReferenceAccountNumber = generateRandomNumber();
            }
        }
        return anotherAttemptCreatingReferenceAccountNumber;
    }
}

package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.exception.account.AccountIllegalSortingRequest;
import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Component
public class AccountUtils {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountUtils(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private boolean isAccountReferenceAccountExists(int referenceAccountNumber) {
        return accountRepository.existsByReferenceAccountNumber(referenceAccountNumber);
    }

    private int generateRandomNumber() {
        int min = 100000;
        int max = 999999;
        return min + new Random().nextInt(max - min + 1);
    }

    public int generateReferenceAccountNumber() {
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
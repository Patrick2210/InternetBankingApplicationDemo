package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
/**
 * Utility class for managing account-related operations.
 */
@Component
public class AccountUtils {

    private final AccountRepository accountRepository;
    /**
     * Constructor for AccountUtils.
     *
     * @param accountRepository The repository for accessing account-related data.
     */
    @Autowired
    public AccountUtils(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    /**
     * Checks if an account with the given reference number exists in the repository.
     *
     * @param referenceAccountNumber The reference account number to check for existence.
     * @return True if the account exists, false otherwise.
     */
    private boolean isAccountReferenceAccountExists(int referenceAccountNumber) {
        return accountRepository.existsByReferenceAccountNumber(referenceAccountNumber);
    }
    /**
     * Generates a random number within a specific range.
     *
     * @return A randomly generated number.
     */
    private int generateRandomNumber() {
        int min = 100000;
        int max = 999999;
        return min + new Random().nextInt(max - min + 1);
    }
    /**
     * Generates a unique reference account number.
     *
     * @return A unique reference account number that does not exist in the repository.
     */
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
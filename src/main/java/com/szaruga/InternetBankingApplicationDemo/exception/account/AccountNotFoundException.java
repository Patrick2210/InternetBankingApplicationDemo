package com.szaruga.InternetBankingApplicationDemo.exception.account;

/**
 * Exception thrown when an account is not found.
 */
public class AccountNotFoundException extends RuntimeException {
    /**
     * Constructs an AccountNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public AccountNotFoundException(String message) {
        super(message);
    }
}

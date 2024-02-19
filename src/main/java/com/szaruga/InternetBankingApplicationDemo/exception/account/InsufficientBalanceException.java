package com.szaruga.InternetBankingApplicationDemo.exception.account;

/**
 * Exception thrown when an account has insufficient balance for an operation.
 */
public class InsufficientBalanceException extends RuntimeException {
    /**
     * Constructs an InsufficientBalanceException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

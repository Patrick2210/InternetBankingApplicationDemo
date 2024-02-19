package com.szaruga.InternetBankingApplicationDemo.exception.user;

/**
 * Exception thrown when a user still has associated accounts and an operation cannot be performed.
 */
public class UserHasAccountsException extends RuntimeException {
    /**
     * Constructs a UserHasAccountsException with the specified detail message.
     *
     * @param message the detail message.
     */
    public UserHasAccountsException(String message) {
        super(message);
    }
}

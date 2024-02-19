package com.szaruga.InternetBankingApplicationDemo.exception.user;

/**
 * Exception thrown when a user is not found in the system.
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * Constructs a UserNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}

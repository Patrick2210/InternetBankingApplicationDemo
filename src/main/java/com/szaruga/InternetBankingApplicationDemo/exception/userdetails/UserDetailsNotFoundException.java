package com.szaruga.InternetBankingApplicationDemo.exception.userdetails;

/**
 * Exception thrown when user details are not found in the system.
 */
public class UserDetailsNotFoundException extends RuntimeException {
    /**
     * Constructs a UserDetailsNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public UserDetailsNotFoundException(String message) {
        super(message);
    }
}

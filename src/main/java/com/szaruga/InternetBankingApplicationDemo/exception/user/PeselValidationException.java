package com.szaruga.InternetBankingApplicationDemo.exception.user;

/**
 * Exception thrown when there is an issue with invalid PESEL validation.
 */
public class PeselValidationException extends RuntimeException {
    /**
     * Constructs an PeselValidationException with the specified detail message.
     *
     * @param message the detail message.
     */
    public PeselValidationException(String message) {
        super(message);
    }
}

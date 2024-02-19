package com.szaruga.InternetBankingApplicationDemo.exception.validation;

/**
 * Exception thrown when there is an issue with validation.
 */
public class ValidationException extends RuntimeException {
    /**
     * Constructs a ValidationException with the specified detail message.
     *
     * @param message the detail message.
     */
    public ValidationException(String message) {
        super(message);
    }
}

package com.szaruga.InternetBankingApplicationDemo.exception.address;

/**
 * Exception thrown when there is an issue with invalid voivodeship.
 */
public class InvalidVoivodeshipException extends RuntimeException {

    /**
     * Constructs an InvalidVoivodeshipException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidVoivodeshipException(String message) {
        super(message);
    }
}

package com.szaruga.InternetBankingApplicationDemo.exception.address;

/**
 * Exception thrown when there is an issue with invalid city.
 */
public class InvalidCityException extends RuntimeException {
    /**
     * Constructs an InvalidAddressException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidCityException(String message) {
        super(message);
    }
}

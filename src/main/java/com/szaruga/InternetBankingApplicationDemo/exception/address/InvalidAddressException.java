package com.szaruga.InternetBankingApplicationDemo.exception.address;

/**
 * Exception thrown when there is an issue with invalid address.
 */
public class InvalidAddressException extends RuntimeException {
    /**
     * Constructs an InvalidAddressException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidAddressException(String message) {
        super(message);
    }
}

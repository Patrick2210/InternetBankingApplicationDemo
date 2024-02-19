package com.szaruga.InternetBankingApplicationDemo.exception.address;

/**
 * Exception thrown when there is an issue with invalid postcode.
 */
public class InvalidPostcodeException extends RuntimeException {
    /**
     * Constructs an InvalidPostcodeException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidPostcodeException(String message) {
        super(message);
    }
}

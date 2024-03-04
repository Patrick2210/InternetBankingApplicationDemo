package com.szaruga.InternetBankingApplicationDemo.exception.address;
/**
 * Exception thrown when there is an issue with invalid county.
 */
public class InvalidCountyException extends RuntimeException{
    /**
     * Constructs an InvalidPostcodeException with the specified detail message.
     *
     * @param message the detail message.
     */

    public InvalidCountyException(String message) {
        super(message);
    }
}

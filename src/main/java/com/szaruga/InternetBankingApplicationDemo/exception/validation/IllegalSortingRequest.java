package com.szaruga.InternetBankingApplicationDemo.exception.validation;

/**
 * Exception thrown when an illegal sorting request is made.
 */
public class IllegalSortingRequest extends RuntimeException {
    /**
     * Constructs an IllegalSortingRequest with the specified detail message.
     *
     * @param message the detail message.
     */
    public IllegalSortingRequest(String message) {
        super(message);
    }
}

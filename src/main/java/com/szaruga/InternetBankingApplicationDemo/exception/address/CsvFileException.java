package com.szaruga.InternetBankingApplicationDemo.exception.address;

/**
 * Exception thrown when there is an issue with the CSV file.
 */
public class CsvFileException extends RuntimeException {
    /**
     * Constructs an CsvFileException with the specified detail message.
     *
     * @param message the detail message.
     */
    public CsvFileException(String message) {
        super(message);
    }
}

package com.szaruga.InternetBankingApplicationDemo.exception.address;

public class InvalidPostcodeException extends RuntimeException {
    public InvalidPostcodeException(String message) {
        super(message);
    }
}

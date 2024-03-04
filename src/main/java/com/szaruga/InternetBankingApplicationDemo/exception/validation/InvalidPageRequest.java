package com.szaruga.InternetBankingApplicationDemo.exception.validation;

public class InvalidPageRequest extends RuntimeException {
    public InvalidPageRequest(String message) {
        super(message);
    }
}

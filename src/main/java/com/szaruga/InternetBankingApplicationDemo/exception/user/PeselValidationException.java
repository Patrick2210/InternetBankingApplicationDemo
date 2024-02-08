package com.szaruga.InternetBankingApplicationDemo.exception.user;

public class PeselValidationException extends RuntimeException {
    public PeselValidationException(String message) {
        super(message);
    }
}

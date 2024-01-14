package com.szaruga.InternetBankingApplicationDemo.exception.account;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

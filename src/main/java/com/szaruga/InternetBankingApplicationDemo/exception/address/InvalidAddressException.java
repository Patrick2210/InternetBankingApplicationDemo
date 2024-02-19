package com.szaruga.InternetBankingApplicationDemo.exception.address;

public class InvalidAddressException extends RuntimeException {
    public InvalidAddressException(String message) {
        super(message);
    }
}

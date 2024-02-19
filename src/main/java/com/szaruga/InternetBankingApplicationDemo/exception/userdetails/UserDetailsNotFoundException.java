package com.szaruga.InternetBankingApplicationDemo.exception.userdetails;

public class UserDetailsNotFoundException extends RuntimeException {
    public UserDetailsNotFoundException(String message) {
        super(message);
    }
}

package com.szaruga.InternetBankingApplicationDemo.exception.user;

public class UserHasAccountsException extends RuntimeException {
    public UserHasAccountsException (String message){
        super(message);
    }
}

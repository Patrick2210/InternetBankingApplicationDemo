package com.szaruga.InternetBankingApplicationDemo.exception.account;

public class AccountIllegalSortingRequest extends RuntimeException {
    public AccountIllegalSortingRequest (String message){
        super(message);
    }
}

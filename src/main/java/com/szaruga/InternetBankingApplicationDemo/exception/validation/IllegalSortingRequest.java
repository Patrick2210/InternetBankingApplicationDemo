package com.szaruga.InternetBankingApplicationDemo.exception.validation;

public class IllegalSortingRequest extends RuntimeException {
    public IllegalSortingRequest(String message){
        super(message);
    }
}

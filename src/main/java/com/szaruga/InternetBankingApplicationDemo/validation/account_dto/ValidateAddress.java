package com.szaruga.InternetBankingApplicationDemo.validation.account_dto;

public class ValidateAddress {

    public static void validate(String address) {
        checkAddress(address);
    }

    private static void checkAddress(String address) {
        //todo jak to ogarnac czy sprawdzac gdzies kody pocztowe aby faktycznie ogarnac rzeczywisty addres?
    }
}

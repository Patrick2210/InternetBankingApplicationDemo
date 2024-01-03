package com.szaruga.InternetBankingApplicationDemo.validation.userdto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.MUST_BE_NOT_NULL;
import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.PHONE_NUMBER;

public class ValidatePhoneNumber {

    public static void validate(String phoneNumber) {
        checkNotNull(phoneNumber);
    }

    private static void checkNotNull(String phoneNumber) {
        if (phoneNumber == null) {
            throw new ValidationException(PHONE_NUMBER.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }
}

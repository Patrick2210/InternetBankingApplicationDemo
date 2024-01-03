package com.szaruga.InternetBankingApplicationDemo.validation.userdto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

public class ValidatePeselNumber {

    public static void validate(String peselNumber) {
        checkNotNull(peselNumber);
        checkNotEmpty(peselNumber);
        checkNumberSize(peselNumber);
    }

    private static void checkNotNull(String peselNumber) {
        if (peselNumber == null) {
            throw new ValidationException(PESEL_NUMBER.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }

    private static void checkNotEmpty(String peselNumber) {
        if (peselNumber.isEmpty()) {
            throw new ValidationException(PESEL_NUMBER.getMessage() + MUST_BE_NOT_EMPTY.getMessage());
        }
    }

    private static void checkNumberSize(String peselNumber) {
        if (peselNumber.length() != 11) {
            throw new ValidationException(PESEL_NUMBER.getMessage() + PESEL_NUMBER_LENGTH.getMessage());
        }
    }
}

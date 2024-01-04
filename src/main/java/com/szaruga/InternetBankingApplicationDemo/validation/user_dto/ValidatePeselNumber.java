package com.szaruga.InternetBankingApplicationDemo.validation.user_dto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

public class ValidatePeselNumber {

    public static void validate(String peselNumber) {
        checkNotNull(peselNumber, PESEL_NUMBER.getMessage());
        checkNotEmpty(peselNumber, PESEL_NUMBER.getMessage());
        checkNumberSize(peselNumber);
    }

    private static void checkNumberSize(String peselNumber) {
        if (peselNumber.length() != 11) {
            throw new ValidationException(PESEL_NUMBER.getMessage() + PESEL_NUMBER_LENGTH.getMessage());
        }
    }

    //Todo sprawdzic czy pesel zgadza sie i jest w bazie polskich peseli?
}

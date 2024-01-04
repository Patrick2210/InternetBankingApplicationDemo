package com.szaruga.InternetBankingApplicationDemo.validation.user_dto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class ValidatePhoneNumber {

    public static void validate(String phoneNumber) {
        checkNotNull(phoneNumber, PHONE_NUMBER.getMessage());
        checkNotEmpty(phoneNumber, PHONE_NUMBER.getMessage());
        checkFormatNumber(phoneNumber);
    }

    private static void checkFormatNumber(String phoneNumber) {
        if (!(phoneNumber.matches("^\\+48\\d{9}$") || phoneNumber.matches("^\\d{9}$"))) {
            throw new ValidationException(PHONE_NUMBER.getMessage() + PHONE_NUMBER_FORMAT.getMessage());
        }
    }
}

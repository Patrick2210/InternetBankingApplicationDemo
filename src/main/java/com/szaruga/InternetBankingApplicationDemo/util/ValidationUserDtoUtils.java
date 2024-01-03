package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

public class ValidationUserDtoUtils {

    public static void checkNotNull(String value, String message) {
        if (value == null) {
            throw new ValidationException(message + MUST_BE_NOT_NULL.getMessage());
        }
    }

    public static void checkNotEmpty(String value, String message) {
        if (value.isEmpty()) {
            throw new ValidationException(message + MUST_BE_NOT_EMPTY.getMessage());
        }
    }

    public static void checkMinSize(String value, String message) {
        int lengthChar = 3;
        if (value.length() < lengthChar) {
            throw new ValidationException(message + MIN_CHAR.getMessage() + lengthChar + CHARACTERS.getMessage());
        }
    }

    public static void checkMaxSize(String value, String message) {
        int lengthChar = 35;
        if (value.length() > lengthChar) {
            throw new ValidationException(value + MAX_CHAR.getMessage() + lengthChar + CHARACTERS.getMessage());
        }
    }
}

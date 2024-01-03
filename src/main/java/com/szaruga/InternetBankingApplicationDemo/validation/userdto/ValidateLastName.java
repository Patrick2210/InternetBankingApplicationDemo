package com.szaruga.InternetBankingApplicationDemo.validation.userdto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

public class ValidateLastName {
    public static void validate(String lastName) {
        checkNotNull(lastName);
        checkNotEmpty(lastName);
        checkMinSize(lastName);
        checkMaxSize(lastName);
        checkSpecialCharacters(lastName);
    }

    private static void checkNotNull(String lastName) {
        if (lastName == null) {
            throw new ValidationException(LAST_NAME.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }

    private static void checkNotEmpty(String lastName) {
        if (lastName.isEmpty()) {
            throw new ValidationException(LAST_NAME.getMessage() + MUST_BE_NOT_EMPTY.getMessage());
        }
    }

    private static void checkMinSize(String lastName) {
        int lengthChar = 3;
        if (lastName.length() < lengthChar) {
            throw new ValidationException(LAST_NAME.getMessage() + MIN_CHAR.getMessage() + lengthChar + CHARACTERS.getMessage());
        }
    }

    private static void checkMaxSize(String lastName) {
        int lengthChar = 25;
        if (lastName.length() > lengthChar) {
            throw new ValidationException(LAST_NAME.getMessage() + MAX_CHAR.getMessage() + lengthChar + CHARACTERS.getMessage());
        }
    }

    private static void checkSpecialCharacters(String lastName) {
        if (!lastName.matches("^[a-zA-Z]+$")) {
            throw new ValidationException(LAST_NAME.getMessage() + SPECIAL_CHARACTERS.getMessage());
        }
    }
}

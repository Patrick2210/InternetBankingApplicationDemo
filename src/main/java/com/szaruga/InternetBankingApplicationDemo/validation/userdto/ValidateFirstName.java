package com.szaruga.InternetBankingApplicationDemo.validation.userdto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

public class ValidateFirstName {
    private static void checkNotNull(String firstName) {
        if (firstName == null) {
            throw new ValidationException(FIRST_NAME.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }

    private static void checkNotEmpty(String firstName) {
        if (firstName.isEmpty()) {
            throw new ValidationException(FIRST_NAME.getMessage() + MUST_BE_NOT_EMPTY.getMessage());
        }
    }

    private static void checkMinSize(String firstName) {
        int lengthChar = 3;
        if (firstName.length() < lengthChar) {
            throw new ValidationException(FIRST_NAME.getMessage() + MIN_CHAR.getMessage() + lengthChar + CHARACTERS.getMessage());
        }
    }

    private static void checkMaxSize(String firstName) {
        int lengthChar = 25;
        if (firstName.length() > lengthChar) {
            throw new ValidationException(FIRST_NAME.getMessage() + MAX_CHAR.getMessage() + lengthChar + CHARACTERS.getMessage());
        }
    }

    private static void checkSpecialCharacters(String firstName) {
        if (!firstName.matches("^[a-zA-Z]+$")) {
            throw new ValidationException(FIRST_NAME.getMessage() + SPECIAL_CHARACTERS.getMessage());
        }
    }

    public static void validateFirstName(String firstName) {
        checkNotNull(firstName);
        checkNotEmpty(firstName);
        checkMinSize(firstName);
        checkMaxSize(firstName);
        checkSpecialCharacters(firstName);
    }
}

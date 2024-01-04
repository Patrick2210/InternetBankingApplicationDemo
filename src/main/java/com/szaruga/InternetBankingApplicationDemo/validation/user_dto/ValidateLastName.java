package com.szaruga.InternetBankingApplicationDemo.validation.user_dto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class ValidateLastName {
    public static void validate(String lastName) {
        checkNotNull(lastName, LAST_NAME.getMessage());
        checkNotEmpty(lastName, LAST_NAME.getMessage());
        checkMinSize(lastName, LAST_NAME.getMessage());
        checkMaxSize(lastName, LAST_NAME.getMessage());
        checkSpecialCharacters(lastName);
    }

    private static void checkSpecialCharacters(String lastName) {
        if (!lastName.matches("^[a-zA-Z]+$")) {
            throw new ValidationException(LAST_NAME.getMessage() + SPECIAL_CHARACTERS.getMessage());
        }
    }
}

package com.szaruga.InternetBankingApplicationDemo.validation.user_dto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class ValidateFirstName {

    public static void validate(String firstName) {
        checkNotNull(firstName, FIRST_NAME.getMessage());
        checkNotEmpty(firstName, FIRST_NAME.getMessage());
        checkMinSize(firstName, FIRST_NAME.getMessage());
        checkMaxSize(firstName, FIRST_NAME.getMessage());
        checkSpecialCharacters(firstName);
    }

    private static void checkSpecialCharacters(String firstName) {
        if (!firstName.matches("^[a-zA-Z]+$")) {
            throw new ValidationException(FIRST_NAME.getMessage() + SPECIAL_CHARACTERS.getMessage());
        }
    }
}

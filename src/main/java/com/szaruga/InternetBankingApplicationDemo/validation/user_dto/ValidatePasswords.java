package com.szaruga.InternetBankingApplicationDemo.validation.user_dto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

public class ValidatePasswords {

    public static void validate(String password, String passwordReset) {
        checkNotNull(password, PASSWORD.getMessage());
        checkNotEmpty(password, PASSWORD.getMessage());
        checkNotNull(passwordReset, PASSWORD_RESET.getMessage());
        checkNotEmpty(passwordReset, PASSWORD_RESET.getMessage());
        isPasswordComplex(password, passwordReset);
    }

    private static void arePasswordsEqual(String password, String passwordReset) {
        if (!passwordReset.equals(password)) {
            throw new ValidationException(PASSWORD_DO_NOT_MATCH.getMessage());
        }
    }

    private static void isPasswordComplex(String password, String passwordReset) {
        arePasswordsEqual(password, passwordReset);
        if (!password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&£])[A-Za-z\\d@$!%*?&£]+$")) {
            throw new ValidationException(PASSWORD_REQUIREMENTS.getMessage());
        }
    }
}

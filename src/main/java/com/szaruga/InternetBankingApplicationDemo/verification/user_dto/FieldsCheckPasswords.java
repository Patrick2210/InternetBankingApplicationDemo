package com.szaruga.InternetBankingApplicationDemo.verification.user_dto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class FieldsCheckPasswords {

    public static void validate(String password, String passwordReset) {
        checkNotNull(password, PASSWORD.getMessage());
        checkNotEmpty(password, PASSWORD.getMessage());
        checkNotNull(passwordReset, PASSWORD_REPEAT.getMessage());
        checkNotEmpty(passwordReset, PASSWORD_REPEAT.getMessage());
        isPasswordComplex(password, passwordReset);
    }
}
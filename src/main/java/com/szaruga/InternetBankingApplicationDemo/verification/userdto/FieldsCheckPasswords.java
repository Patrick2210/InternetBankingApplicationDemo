package com.szaruga.InternetBankingApplicationDemo.verification.userdto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

public class FieldsCheckPasswords {

    public static void validate(String password, String passwordReset) {
        checkNotNull(password, PASSWORD.getMessage());
        checkNotEmpty(password, PASSWORD.getMessage());
        checkNotNull(passwordReset, PASSWORD_REPEAT.getMessage());
        checkNotEmpty(passwordReset, PASSWORD_REPEAT.getMessage());
        isPasswordComplex(password, passwordReset);
    }
}
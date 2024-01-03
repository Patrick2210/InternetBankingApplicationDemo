package com.szaruga.InternetBankingApplicationDemo.validation.userdto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;
import com.szaruga.InternetBankingApplicationDemo.util.PasswordEncryptionUtil;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class ValidatePasswords {

    public static void validate(String password, String passwordReset) {
        checkNotNull(password, PASSWORD.getMessage());
        checkNotEmpty(password, PASSWORD.getMessage());
        checkNotNull(passwordReset, PASSWORD_RESET.getMessage());
        checkNotEmpty(passwordReset, PASSWORD_RESET.getMessage());
        isPasswordComplex(password, passwordReset);
    }

    private static boolean arePasswordsEqual(String password, String passwordReset) {
        if (password.equals(passwordReset)) {
            return true;
        } else throw new ValidationException(PASSWORD_MATCH.getMessage());
    }

    private static boolean isPasswordComplex(String password, String passwordReset) {
        arePasswordsEqual(password, passwordReset);
        if (password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&£])[A-Za-z\\d@$!%*?&£]+$")) {
            return true;
        } else throw new ValidationException(PASSWORD_REQUIREMENTS.getMessage());

    }
}

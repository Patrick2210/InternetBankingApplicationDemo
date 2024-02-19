package com.szaruga.InternetBankingApplicationDemo.verification.userdto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;
/**
 * Utility class for verifying and validating user passwords.
 */
public class FieldsCheckPasswords {
    /**
     * Validates the provided passwords.
     *
     * @param password      The password to validate.
     * @param passwordReset The password reset confirmation to validate.
     */
    public static void validate(String password, String passwordReset) {
        checkNotNull(password, PASSWORD.getMessage());
        checkNotEmpty(password, PASSWORD.getMessage());
        checkNotNull(passwordReset, PASSWORD_REPEAT.getMessage());
        checkNotEmpty(passwordReset, PASSWORD_REPEAT.getMessage());
        isPasswordComplex(password, passwordReset);
    }
}
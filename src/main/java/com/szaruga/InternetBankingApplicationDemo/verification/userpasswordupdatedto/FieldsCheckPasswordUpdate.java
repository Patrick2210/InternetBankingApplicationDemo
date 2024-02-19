package com.szaruga.InternetBankingApplicationDemo.verification.userpasswordupdatedto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;
/**
 * Utility class for verifying and validating updated user passwords.
 */
public class FieldsCheckPasswordUpdate {
    /**
     * Validates the provided passwords.
     *
     * @param password      The password to validate.
     * @param repeatPassword The password reset confirmation to validate.
     */
    public static void validate(String password, String repeatPassword) {
        checkNotNull(password, PASSWORD.getMessage());
        checkNotEmpty(password, PASSWORD.getMessage());
        checkNotNull(repeatPassword, PASSWORD_REPEAT.getMessage());
        checkNotEmpty(repeatPassword, PASSWORD_REPEAT.getMessage());
        isPasswordComplex(password, repeatPassword);
    }
}

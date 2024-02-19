package com.szaruga.InternetBankingApplicationDemo.verification.userdto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;
/**
 * Utility class for verifying and validating user email addresses.
 */
public class FieldCheckEmail {
    /**
     * Validates the provided email address.
     *
     * @param email The email address to validate.
     */
    public static void validate(String email) {
        checkNotNull(email, EMAIL.getMessage());
        checkNotEmpty(email, EMAIL.getMessage());
        isValidEmail(email, EMAIL.getMessage());
    }
}

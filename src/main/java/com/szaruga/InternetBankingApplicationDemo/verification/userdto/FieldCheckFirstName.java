package com.szaruga.InternetBankingApplicationDemo.verification.userdto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;
/**
 * Utility class for verifying and validating user firstname.
 */
public class FieldCheckFirstName {
    /**
     * Validates the provided email address.
     *
     * @param firstName The firstname to validate.
     */
    public static void validate(String firstName) {
        checkNotNull(firstName, FIRST_NAME.getMessage());
        checkNotEmpty(firstName, FIRST_NAME.getMessage());
        checkMinSize(firstName, FIRST_NAME.getMessage());
        checkMaxSize(firstName, FIRST_NAME.getMessage());
        checkSpecialCharacters(firstName, FIRST_NAME.getMessage());
    }
}

package com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.FIRST_NAME;
import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.LAST_NAME;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

/**
 * Utility class for verifying and validating updated user last names.
 */
public class FieldCheckLastNameUpdate {
    /**
     * Validates the provided last name.
     *
     * @param lastName The last name to validate.
     */
    public static void validate(String lastName) {
        checkNotNull(lastName, LAST_NAME.getMessage());
        checkNotEmpty(lastName, LAST_NAME.getMessage());
        checkMinSize(lastName, LAST_NAME.getMessage());
        checkMaxSize(lastName, LAST_NAME.getMessage());
        checkSpecialCharacters(lastName, FIRST_NAME.getMessage());
    }
}

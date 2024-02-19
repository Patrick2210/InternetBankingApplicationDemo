package com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.FIRST_NAME;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

/**
 * Utility class for verifying and validating updated user first names.
 */
public class FieldCheckFirstNameUpdate {
    /**
     * Validates the provided first name.
     *
     * @param firstName The first name to validate.
     */
    public static void validate(String firstName) {
        checkNotNull(firstName, FIRST_NAME.getMessage());
        checkNotEmpty(firstName, FIRST_NAME.getMessage());
        checkMinSize(firstName, FIRST_NAME.getMessage());
        checkMaxSize(firstName, FIRST_NAME.getMessage());
        checkSpecialCharacters(firstName, FIRST_NAME.getMessage());
    }
}

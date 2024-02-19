package com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.PHONE_NUMBER;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

/**
 * Utility class for verifying and validating updated user phone numbers.
 */
public class FieldCheckPhoneNumberUpdate {
    /**
     * Validates the provided phone number.
     *
     * @param phoneNumber The phone number to validate.
     */
    public static void validate(String phoneNumber) {
        checkNotNull(phoneNumber, PHONE_NUMBER.getMessage());
        checkNotEmpty(phoneNumber, PHONE_NUMBER.getMessage());
        checkFormatNumber(phoneNumber, PHONE_NUMBER.getMessage());
    }
}

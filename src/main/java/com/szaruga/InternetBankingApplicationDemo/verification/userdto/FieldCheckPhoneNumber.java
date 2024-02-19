package com.szaruga.InternetBankingApplicationDemo.verification.userdto;


import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;
/**
 * Utility class for verifying and validating user phone number.
 */
public class FieldCheckPhoneNumber {
    /**
     * Validates the provided email address.
     *
     * @param phoneNumber The firstname to validate.
     */
    public static void validate(String phoneNumber) {
        checkNotNull(phoneNumber, PHONE_NUMBER.getMessage());
        checkNotEmpty(phoneNumber, PHONE_NUMBER.getMessage());
        checkFormatNumber(phoneNumber, PHONE_NUMBER.getMessage());
    }
}

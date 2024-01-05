package com.szaruga.InternetBankingApplicationDemo.verification.user_upgrade_dto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.PHONE_NUMBER;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class FieldCheckPhoneNumberUpdate {
    public static void validate(String phoneNumber) {
        checkNotNull(phoneNumber, PHONE_NUMBER.getMessage());
        checkNotEmpty(phoneNumber, PHONE_NUMBER.getMessage());
        checkFormatNumber(phoneNumber, PHONE_NUMBER.getMessage());
    }
}

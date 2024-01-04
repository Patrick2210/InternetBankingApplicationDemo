package com.szaruga.InternetBankingApplicationDemo.validation.user_upgrade_dto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.PHONE_NUMBER;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

public class ValidateUpdatePhoneNumber {
    public static void validate(String phoneNumber) {
        checkNotNull(phoneNumber, PHONE_NUMBER.getMessage());
        checkNotEmpty(phoneNumber, PHONE_NUMBER.getMessage());
        checkFormatNumber(phoneNumber, PHONE_NUMBER.getMessage());
    }
}

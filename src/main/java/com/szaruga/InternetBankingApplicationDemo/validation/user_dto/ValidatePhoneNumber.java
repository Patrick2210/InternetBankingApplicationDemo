package com.szaruga.InternetBankingApplicationDemo.validation.user_dto;


import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

public class ValidatePhoneNumber {

    public static void validate(String phoneNumber) {
        checkNotNull(phoneNumber, PHONE_NUMBER.getMessage());
        checkNotEmpty(phoneNumber, PHONE_NUMBER.getMessage());
        checkFormatNumber(phoneNumber, PHONE_NUMBER.getMessage());
    }
}

package com.szaruga.InternetBankingApplicationDemo.verification.user_dto;


import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class FieldCheckPhoneNumber {

    public static void validate(String phoneNumber) {
        checkNotNull(phoneNumber, PHONE_NUMBER.getMessage());
        checkNotEmpty(phoneNumber, PHONE_NUMBER.getMessage());
        checkFormatNumber(phoneNumber, PHONE_NUMBER.getMessage());
    }
}

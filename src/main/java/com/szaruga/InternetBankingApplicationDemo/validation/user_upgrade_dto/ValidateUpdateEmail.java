package com.szaruga.InternetBankingApplicationDemo.validation.user_upgrade_dto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.EMAIL;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

public class ValidateUpdateEmail {
    public static void validate(String email) {
        checkNotNull(email, EMAIL.getMessage());
        checkNotEmpty(email, EMAIL.getMessage());
        isValidEmail(email, EMAIL.getMessage());
    }
}

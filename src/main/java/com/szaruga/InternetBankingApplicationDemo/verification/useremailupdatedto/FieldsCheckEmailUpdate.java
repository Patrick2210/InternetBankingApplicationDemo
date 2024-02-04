package com.szaruga.InternetBankingApplicationDemo.verification.useremailupdatedto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.EMAIL;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

public class FieldsCheckEmailUpdate {

    public static void validate(String email) {
        checkNotNull(email, EMAIL.getMessage());
        checkNotEmpty(email, EMAIL.getMessage());
        isValidEmail(email, EMAIL.getMessage());
    }
}

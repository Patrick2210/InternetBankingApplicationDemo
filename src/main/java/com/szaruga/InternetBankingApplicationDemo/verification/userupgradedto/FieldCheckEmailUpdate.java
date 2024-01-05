package com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.EMAIL;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class FieldCheckEmailUpdate {
    public static void validate(String email) {
        checkNotNull(email, EMAIL.getMessage());
        checkNotEmpty(email, EMAIL.getMessage());
        isValidEmail(email, EMAIL.getMessage());
    }
}

package com.szaruga.InternetBankingApplicationDemo.verification.userdto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class FieldCheckEmail {

    public static void validate(String email) {
        checkNotNull(email, EMAIL.getMessage());
        checkNotEmpty(email, EMAIL.getMessage());
        isValidEmail(email, EMAIL.getMessage());
    }
}

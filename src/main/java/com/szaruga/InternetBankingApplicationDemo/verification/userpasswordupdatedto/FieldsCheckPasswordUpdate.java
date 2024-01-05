package com.szaruga.InternetBankingApplicationDemo.verification.userpasswordupdatedto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class FieldsCheckPasswordUpdate {
    public static void validate(String password, String repeatPassword) {
        checkNotNull(password, PASSWORD.getMessage());
        checkNotEmpty(password, PASSWORD.getMessage());
        checkNotNull(repeatPassword, PASSWORD_REPEAT.getMessage());
        checkNotEmpty(repeatPassword, PASSWORD_REPEAT.getMessage());
        isPasswordComplex(password, repeatPassword);
    }
}

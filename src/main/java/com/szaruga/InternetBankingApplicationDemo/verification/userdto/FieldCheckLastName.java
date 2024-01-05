package com.szaruga.InternetBankingApplicationDemo.verification.userdto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class FieldCheckLastName {
    public static void validate(String lastName) {
        checkNotNull(lastName, LAST_NAME.getMessage());
        checkNotEmpty(lastName, LAST_NAME.getMessage());
        checkMinSize(lastName, LAST_NAME.getMessage());
        checkMaxSize(lastName, LAST_NAME.getMessage());
        checkSpecialCharacters(lastName, FIRST_NAME.getMessage());
    }
}

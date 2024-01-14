package com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.FIRST_NAME;
import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.LAST_NAME;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

public class FieldCheckLastNameUpdate {
    public static void validate(String lastName) {
        checkNotNull(lastName, LAST_NAME.getMessage());
        checkNotEmpty(lastName, LAST_NAME.getMessage());
        checkMinSize(lastName, LAST_NAME.getMessage());
        checkMaxSize(lastName, LAST_NAME.getMessage());
        checkSpecialCharacters(lastName, FIRST_NAME.getMessage());
    }
}

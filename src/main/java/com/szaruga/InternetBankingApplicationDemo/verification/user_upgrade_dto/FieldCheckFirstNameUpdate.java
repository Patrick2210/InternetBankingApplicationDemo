package com.szaruga.InternetBankingApplicationDemo.verification.user_upgrade_dto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.FIRST_NAME;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class FieldCheckFirstNameUpdate {
    public static void validate(String firstName) {
        checkNotNull(firstName, FIRST_NAME.getMessage());
        checkNotEmpty(firstName, FIRST_NAME.getMessage());
        checkMinSize(firstName, FIRST_NAME.getMessage());
        checkMaxSize(firstName, FIRST_NAME.getMessage());
        checkSpecialCharacters(firstName, FIRST_NAME.getMessage());
    }
}

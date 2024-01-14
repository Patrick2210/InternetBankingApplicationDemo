package com.szaruga.InternetBankingApplicationDemo.verification.accountdto;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.ACCOUNT_TYPE;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

public class FiledCheckAccountType {
    public static void validate(String accountType) {
        checkNotNull(accountType, ACCOUNT_TYPE.getMessage());
        checkNotEmpty(accountType, ACCOUNT_TYPE.getMessage());
        checkMinSize(accountType, ACCOUNT_TYPE.getMessage());
        checkMaxSize(accountType, ACCOUNT_TYPE.getMessage());
        checkSpecialCharacters(accountType,ACCOUNT_TYPE.getMessage());
    }
}

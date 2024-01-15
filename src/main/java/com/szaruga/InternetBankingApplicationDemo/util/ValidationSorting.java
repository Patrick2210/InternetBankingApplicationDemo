package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.exception.account.AccountIllegalSortingRequest;

import java.util.Arrays;
import java.util.List;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.NOT_ALLOWED;

public class ValidationSorting {
    private static final List<String> allowedSortingList = Arrays.asList(
            ACCOUNT_TYPE.getMessage(),
            REFERENCE_ACCOUNT_NUMBER.getMessage()
    );

    public static String replaceAllWhiteSpaces(String input) {
        return input.replaceAll("\\s", "");
    }

    public static boolean validateSorting(String sort) {
        for (String allowedString : allowedSortingList) {
            String allowedSorting = replaceAllWhiteSpaces(allowedString);
            if (allowedSorting.equalsIgnoreCase(sort)) {
                return true;
            }
        }
        throw new AccountIllegalSortingRequest(SORTING_BY.getMessage() + sort + NOT_ALLOWED.getMessage());
    }
}

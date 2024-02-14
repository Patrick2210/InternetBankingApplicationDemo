package com.szaruga.InternetBankingApplicationDemo.util;

import java.util.Arrays;
import java.util.List;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
/**
 * Utility class for defining sorting criteria for string values.
 */
public class SortingStringValues {
    /**
     * List of sorting messages defining the sorting criteria.
     */
    public static final List<String> sortingMessages = Arrays.asList(
            SORTING_ID.getMessage(),
            SORTING_REFERENCE_ACCOUNT_NUMBER.getMessage(),
            POSTCODE.getMessage(),
            CITY.getMessage(),
            SORTING_FIRSTNAME.getMessage(),
            SORTING_LASTNAME.getMessage(),
            SORTING_BIRTHDATE.getMessage()
    );
}

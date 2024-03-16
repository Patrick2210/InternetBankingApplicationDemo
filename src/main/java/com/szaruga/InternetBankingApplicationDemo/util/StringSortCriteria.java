package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.IllegalSortingRequest;

import java.util.Arrays;
import java.util.List;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Utility class for defining sorting criteria for string values.
 */
public class StringSortCriteria {
    /**
     * List of sorting messages defining the sorting criteria.
     */
    protected static final List<String> sortingMessages = Arrays.asList(
            SORTING_ID.getMessage(),
            SORTING_REFERENCE_ACCOUNT_NUMBER.getMessage(),
            POSTCODE.getMessage(),
            CITY.getMessage(),
            SORTING_FIRSTNAME.getMessage(),
            SORTING_LASTNAME.getMessage(),
            SORTING_BIRTHDATE.getMessage()
    );

    /**
     * Preprocesses the sorting criteria provided as input.
     *
     * @param sortByInput The sorting criteria input.
     * @return The preprocessed sorting criteria.
     * @throws IllegalSortingRequest if the sorting criteria is invalid.
     */
    public static String preprocessSortingCriteria(String sortByInput) {
        //todo ask sensei
        for (String s : sortingMessages) {
            if (s.equals(sortByInput)) {
                return sortByInput;
            }
            if (s.equalsIgnoreCase(sortByInput)) {
                return s;
            }
        }
        throw new IllegalSortingRequest(INVALID_SORT_FIELD.getMessage() + sortByInput);
    }
}

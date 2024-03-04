package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.InvalidPageRequest;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.PAGE_REQUEST;

/**
 * Utility class for validating pageable size.
 */
@Component
public class ValidationPageableInput {
    /**
     * Validates the page number and page size.
     *
     * @param pageNumber The page number.
     * @param pageSize   The page size.
     * @throws InvalidPageRequest if either pageNumber or pageSize is not a positive number.
     */
    public void validate(int pageNumber, int pageSize) {
        checkPageRequest(pageNumber, pageSize);
    }

    /**
     * Checks if the page request is valid.
     *
     * @param pageNumber The page number.
     * @param pageSize   The page size.
     * @throws InvalidPageRequest if either pageNumber or pageSize is not a positive number.
     */
    private void checkPageRequest(int pageNumber, int pageSize) {
        if (pageNumber <= 0 && pageSize <= 0) {
            throw new InvalidPageRequest(PAGE_REQUEST.getMessage());
        }
    }
}

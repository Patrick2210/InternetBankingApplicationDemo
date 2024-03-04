package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.COUNTY;

/**
 * Utility class for verifying and validating user county.
 */
public class FiledCheckCount {

    /**
     * Validates the provided address flat number.
     *
     * @param county The address flat number to validate.
     */
    public static void validate(String county) {
        ValidationDtoUtils.checkNotNull(county, COUNTY.getMessage());
        ValidationDtoUtils.checkNotEmpty(county, COUNTY.getMessage());
        ValidationDtoUtils.checkIfContainsSpecialCharacters(county, COUNTY.getMessage());
    }
}

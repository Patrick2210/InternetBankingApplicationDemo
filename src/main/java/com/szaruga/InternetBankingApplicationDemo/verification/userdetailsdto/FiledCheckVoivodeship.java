package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.VOIVODESHIP;

/**
 * Utility class for verifying and validating user voivodeship.
 */
public class FiledCheckVoivodeship {

    /**
     * Validates the provided address flat number.
     *
     * @param voivodeship The address flat number to validate.
     */
    public static void validate(String voivodeship) {
        ValidationDtoUtils.checkNotNull(voivodeship, VOIVODESHIP.getMessage());
        ValidationDtoUtils.checkNotEmpty(voivodeship, VOIVODESHIP.getMessage());
        ValidationDtoUtils.checkIfContainsSpecialCharacters(voivodeship, VOIVODESHIP.getMessage());
    }
}

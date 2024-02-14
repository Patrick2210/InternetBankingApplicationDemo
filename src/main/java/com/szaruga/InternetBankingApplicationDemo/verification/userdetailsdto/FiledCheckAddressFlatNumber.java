package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Utility class for verifying and validating user address flat numbers.
 */
@Component
public class FiledCheckAddressFlatNumber {
    /**
     * Validates the provided address flat number.
     *
     * @param addressFlatNumber The address flat number to validate.
     */
    public static void validate(String addressFlatNumber) {
        ValidationDtoUtils.checkNotNull(addressFlatNumber, ADDRESS_FLAT_NUMBER.getMessage());
        ValidationDtoUtils.checkNotEmpty(addressFlatNumber, ADDRESS_FLAT_NUMBER.getMessage());
        ValidationDtoUtils.checkIfContainsSpecialCharacters(addressFlatNumber, ADDRESS_FLAT_NUMBER.getMessage());
    }
}
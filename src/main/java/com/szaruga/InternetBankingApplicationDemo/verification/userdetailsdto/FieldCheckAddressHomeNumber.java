package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Utility class for verifying and validating user home address numbers.
 */
@Component
public class FieldCheckAddressHomeNumber {
    /**
     * Validates the provided home address number.
     *
     * @param addressHomeNumber The home address number to validate.
     */
    public static void validate(String addressHomeNumber) {
        ValidationDtoUtils.checkNotNull(addressHomeNumber, ADDRESS_HOME_NUMBER.getMessage());
        ValidationDtoUtils.checkNotEmpty(addressHomeNumber, ADDRESS_HOME_NUMBER.getMessage());
        ValidationDtoUtils.checkIfContainsSpecialCharacters(addressHomeNumber, ADDRESS_HOME_NUMBER.getMessage());
    }
}
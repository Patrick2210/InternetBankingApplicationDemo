package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Utility class for verifying and validating user correspondence address home numbers.
 */
@Component
public class FieldCheckCorrespondenceAddressHomeNumber {
    /**
     * Validates the provided correspondence address home number.
     *
     * @param correspondenceAddressHomeNumber The correspondence address home number to validate.
     */
    public static void validate(String correspondenceAddressHomeNumber) {
        ValidationDtoUtils.checkNotNull(correspondenceAddressHomeNumber, CORRESPONDENCE_ADDRESS_HOUSE_NUMBER.getMessage());
        ValidationDtoUtils.checkNotEmpty(correspondenceAddressHomeNumber, CORRESPONDENCE_ADDRESS_HOUSE_NUMBER.getMessage());
        ValidationDtoUtils.checkIfContainsSpecialCharacters(correspondenceAddressHomeNumber, CORRESPONDENCE_ADDRESS_HOUSE_NUMBER.getMessage());
    }
}
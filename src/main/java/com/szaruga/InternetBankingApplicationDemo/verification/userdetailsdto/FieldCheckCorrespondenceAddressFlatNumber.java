package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
@Component
public class FieldCheckCorrespondenceAddressFlatNumber {
    public static void validate(String correspondenceAddressFlatNumber) {
        ValidationDtoUtils.checkNotNull(correspondenceAddressFlatNumber, CORRESPONDENCE_ADDRESS_FLAT_NUMBER.getMessage());
        ValidationDtoUtils.checkNotEmpty(correspondenceAddressFlatNumber, CORRESPONDENCE_ADDRESS_FLAT_NUMBER.getMessage());
        ValidationDtoUtils.checkIfContainsSpecialCharacters(correspondenceAddressFlatNumber, CORRESPONDENCE_ADDRESS_FLAT_NUMBER.getMessage());
    }
}

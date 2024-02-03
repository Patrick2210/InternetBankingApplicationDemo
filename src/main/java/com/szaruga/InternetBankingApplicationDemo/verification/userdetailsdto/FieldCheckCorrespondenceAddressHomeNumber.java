package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Component
public class FieldCheckCorrespondenceAddressHomeNumber {
    public static void validate(String correspondenceAddressHomeNumber) {
        ValidationDtoUtils.checkNotNull(correspondenceAddressHomeNumber, CORRESPONDENCE_ADDRESS_HOUSE_NUMBER.getMessage());
        ValidationDtoUtils.checkNotEmpty(correspondenceAddressHomeNumber, CORRESPONDENCE_ADDRESS_HOUSE_NUMBER.getMessage());
        ValidationDtoUtils.checkIfContainsSpecialCharacters(correspondenceAddressHomeNumber, CORRESPONDENCE_ADDRESS_HOUSE_NUMBER.getMessage());
    }
}
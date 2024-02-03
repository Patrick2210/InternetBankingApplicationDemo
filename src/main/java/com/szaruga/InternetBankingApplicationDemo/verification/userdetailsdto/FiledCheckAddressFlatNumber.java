package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Component
public class FiledCheckAddressFlatNumber {

    public static void validate(String addressFlatNumber) {
        ValidationDtoUtils.checkNotNull(addressFlatNumber, ADDRESS_FLAT_NUMBER.getMessage());
        ValidationDtoUtils.checkNotEmpty(addressFlatNumber, ADDRESS_FLAT_NUMBER.getMessage());
        ValidationDtoUtils.checkIfContainsSpecialCharacters(addressFlatNumber, ADDRESS_FLAT_NUMBER.getMessage());
    }
}
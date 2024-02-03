package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.exception.address.InvalidPostcodeException;
import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Component
public class FieldCheckPostCode {

    private final AddressService addressService;

    @Autowired
    public FieldCheckPostCode(AddressService addressService) {
        this.addressService = addressService;
    }

    public void validate(String postcode) {
        ValidationDtoUtils.checkNotNull(postcode, POSTCODE.getMessage());
        ValidationDtoUtils.checkNotEmpty(postcode, POSTCODE.getMessage());
        checkPostcode(postcode);
    }

    private void checkPostcode(String postcode) {
        boolean postcodeExists = addressService.existsByPostcode(postcode);
        if (!postcodeExists) {
            throw new InvalidPostcodeException(INVALID.getMessage() + POSTCODE.getMessage());
        }
    }
}

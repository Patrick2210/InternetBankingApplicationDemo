package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.exception.address.InvalidPostcodeException;
import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Utility class for verifying and validating user postcodes.
 */
@Component
public class FieldCheckPostCode {

    private final AddressService addressService;

    /**
     * Constructor for FieldCheckPostCode.
     *
     * @param addressService The service for accessing address-related data.
     */
    @Autowired
    public FieldCheckPostCode(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Validates the provided postcode.
     *
     * @param postcode The postcode to validate.
     * @throws InvalidPostcodeException If the postcode is invalid.
     */
    public void validate(String postcode) {
        ValidationDtoUtils.checkNotNull(postcode, POSTCODE.getMessage());
        ValidationDtoUtils.checkNotEmpty(postcode, POSTCODE.getMessage());
        checkPostcode(postcode);
    }

    /**
     * Checks if the provided postcode exists in the database.
     *
     * @param postcode The postcode to check.
     * @throws InvalidPostcodeException If the postcode does not exist in the database.
     */
    private void checkPostcode(String postcode) {
        boolean postcodeExists = addressService.existsByPostcode(postcode);
        if (!postcodeExists) {
            throw new InvalidPostcodeException(INVALID.getMessage() + POSTCODE.getMessage());
        }
    }
}
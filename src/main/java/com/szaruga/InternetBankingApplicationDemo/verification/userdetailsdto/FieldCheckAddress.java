package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.exception.address.InvalidAddressException;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Utility class for verifying and validating user address fields.
 */
@Component
public class FieldCheckAddress {
    private final AddressService addressService;

    /**
     * Constructor for FieldCheckAddress.
     *
     * @param addressService The service for accessing address-related data.
     */
    @Autowired
    public FieldCheckAddress(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Validates the provided address string.
     *
     * @param address The address string to validate.
     * @throws InvalidAddressException If the address is invalid.
     */
    public void validate(String address) {
        ValidationDtoUtils.checkNotNull(address, ADDRESS.getMessage());
        ValidationDtoUtils.checkNotEmpty(address, ADDRESS.getMessage());
        checkAddress(address);
    }

    /**
     * Checks if the provided address exists in the database.
     *
     * @param address The address to check.
     * @throws InvalidAddressException If the address does not exist in the database.
     */
    public void checkAddress(String address) {
        boolean addressExists = addressService.existsByAddress(address);
        if (!addressExists) {
            throw new InvalidAddressException(INVALID.getMessage() + ADDRESS.getMessage());
        }
    }
}
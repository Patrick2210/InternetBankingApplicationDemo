package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.exception.address.InvalidAddressException;
import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Utility class for verifying and validating user correspondence addresses.
 */
@Component
public class FieldCheckCorrespondenceAddress {
    private final AddressService addressService;

    /**
     * Constructor for FieldCheckCorrespondenceAddress.
     *
     * @param addressService The service for accessing address-related data.
     */
    @Autowired
    public FieldCheckCorrespondenceAddress(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Validates the provided correspondence address.
     *
     * @param correspondenceAddress The correspondence address to validate.
     * @throws InvalidAddressException If the correspondence address is invalid.
     */
    public void validate(String correspondenceAddress) {
        ValidationDtoUtils.checkNotNull(correspondenceAddress, CORRESPONDENCE_ADDRESS.getMessage());
        ValidationDtoUtils.checkNotEmpty(correspondenceAddress, CORRESPONDENCE_ADDRESS.getMessage());
        checkCorrespondenceAddress(correspondenceAddress);
    }

    /**
     * Checks if the provided correspondence address exists in the database.
     *
     * @param correspondenceAddress The correspondence address to check.
     * @throws InvalidAddressException If the correspondence address does not exist in the database.
     */
    private void checkCorrespondenceAddress(String correspondenceAddress) {
        boolean correspondenceAddressExist = addressService.existsByAddress(correspondenceAddress);
        if (!correspondenceAddressExist) {
            throw new InvalidAddressException(INVALID.getMessage() + ADDRESS.getMessage());
        }
    }
}
package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.exception.address.InvalidVoivodeshipException;
import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Utility class for verifying and validating user voivodeship.
 */
public class FiledCheckVoivodeship {

    private final AddressService addressService;

    public FiledCheckVoivodeship(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Validates the provided address flat number.
     *
     * @param voivodeship The address flat number to validate.
     */
    public  void validate(String voivodeship) {
        ValidationDtoUtils.checkNotNull(voivodeship, VOIVODESHIP.getMessage());
        ValidationDtoUtils.checkNotEmpty(voivodeship, VOIVODESHIP.getMessage());
        ValidationDtoUtils.checkIfContainsSpecialCharacters(voivodeship, VOIVODESHIP.getMessage());
        checkVoivodeship(voivodeship);
    }

    private void checkVoivodeship(String voivodeship) {
        boolean isVoivodeshipExist = addressService.existsByVoivodeship(voivodeship);
        if (!isVoivodeshipExist) {
            throw new InvalidVoivodeshipException(INVALID.getMessage() + VOIVODESHIP.getMessage());
        }
    }
}

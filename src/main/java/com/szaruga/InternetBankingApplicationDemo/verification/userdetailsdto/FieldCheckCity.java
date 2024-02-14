package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.exception.address.InvalidCityException;
import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Utility class for verifying and validating user city fields.
 */
@Component
public class FieldCheckCity {

    private final AddressService addressService;

    /**
     * Constructor for FieldCheckCity.
     *
     * @param addressService The service for accessing address-related data.
     */
    @Autowired
    public FieldCheckCity(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Validates the provided city string.
     *
     * @param city The city string to validate.
     * @throws InvalidCityException If the city is invalid.
     */
    public void validate(String city) {
        ValidationDtoUtils.checkNotNull(city, CITY.getMessage());
        ValidationDtoUtils.checkNotEmpty(city, CITY.getMessage());
        checkCity(city);
    }

    /**
     * Checks if the provided city exists in the database.
     *
     * @param city The city to check.
     * @throws InvalidCityException If the city does not exist in the database.
     */
    private void checkCity(String city) {
        boolean cityExists = addressService.existsByCity(city);
        if (!cityExists) {
            throw new InvalidCityException(INVALID.getMessage() + CITY.getMessage());
        }
    }
}
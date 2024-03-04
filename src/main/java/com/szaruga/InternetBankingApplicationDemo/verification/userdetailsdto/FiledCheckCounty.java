package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.exception.address.InvalidCountyException;
import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Utility class for verifying and validating user county.
 */
@Component
public class FiledCheckCounty {

    private final AddressService addressService;

    public FiledCheckCounty(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Validates the provided address flat number.
     *
     * @param county The address flat number to validate.
     */
    public void validate(String county) {
        ValidationDtoUtils.checkNotNull(county, COUNTY.getMessage());
        ValidationDtoUtils.checkNotEmpty(county, COUNTY.getMessage());
        ValidationDtoUtils.checkIfContainsSpecialCharacters(county, COUNTY.getMessage());
        checkCounty(county);
    }

    /**
     * Checks if the provided county exists in the database.
     *
     * @param county The county to check.
     * @throws InvalidCountyException If the county does not exist in the database.
     */
    private void checkCounty(String county) {
        boolean countyExist = addressService.existsByCounty(county);
        if (!countyExist) {
            throw new InvalidCountyException(INVALID.getMessage() + COUNTY.getMessage());
        }
    }
}

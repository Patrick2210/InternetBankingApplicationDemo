package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.exception.address.InvalidCityException;
import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Component
public class FieldCheckCity {

    private final AddressService addressService;

    @Autowired
    public FieldCheckCity(AddressService addressService) {
        this.addressService = addressService;
    }

    public void validate(String city) {
        ValidationDtoUtils.checkNotNull(city, CITY.getMessage());
        ValidationDtoUtils.checkNotEmpty(city, CITY.getMessage());
        checkCity(city);
    }

    private void checkCity(String city) {
        boolean cityExists = addressService.existsByCity(city);
        if (!cityExists){
            throw new InvalidCityException(INVALID.getMessage() + CITY.getMessage());
        }
    }
}
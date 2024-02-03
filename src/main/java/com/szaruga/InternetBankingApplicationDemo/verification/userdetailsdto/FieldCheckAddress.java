package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.exception.address.InvalidAddressException;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Component
public class FieldCheckAddress {
    private final AddressService addressService;
    private final Logger logger = LoggerFactory.getLogger(FieldCheckAddress.class);

    @Autowired
    public FieldCheckAddress(AddressService addressService) {
        this.addressService = addressService;
    }

    public void validate(String address) {
        ValidationDtoUtils.checkNotNull(address, ADDRESS.getMessage());
        ValidationDtoUtils.checkNotEmpty(address, ADDRESS.getMessage());
        checkAddress(address);
    }

    public void checkAddress(String address) {
        boolean addressExists = addressService.existsByAddress(address);
        if (!addressExists) {
            throw new InvalidAddressException(INVALID.getMessage() + ADDRESS.getMessage());
        }
        /**
         * // todo czy taki pomsyl jest dobry
         * if (addressExists) {
         *             logger.info(VALID.getMessage() + ADDRESS.getMessage(), address);
         *         } else {
         *             logger.warn(INVALID.getMessage() + ADDRESS.getMessage(), address);
         *             throw new InvalidAddressException(INVALID.getMessage() + ADDRESS.getMessage());
         *         }
         * */

    }
}

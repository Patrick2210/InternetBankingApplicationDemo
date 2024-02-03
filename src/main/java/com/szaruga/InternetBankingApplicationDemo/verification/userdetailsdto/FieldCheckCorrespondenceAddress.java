package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.exception.address.InvalidAddressException;
import com.szaruga.InternetBankingApplicationDemo.service.AddressService;
import com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Component
public class FieldCheckCorrespondenceAddress {
    private final AddressService addressService;

    @Autowired
    public FieldCheckCorrespondenceAddress(AddressService addressService) {
        this.addressService = addressService;
    }
    public void validate(String correspondenceAddress){
        ValidationDtoUtils.checkNotNull(correspondenceAddress, CORRESPONDENCE_ADDRESS.getMessage());
        ValidationDtoUtils.checkNotEmpty(correspondenceAddress, CORRESPONDENCE_ADDRESS.getMessage());
        checkCorrespondenceAddress(correspondenceAddress);
    }

    private void checkCorrespondenceAddress(String correspondenceAddress) {
        boolean correspondenceAddressExist = addressService.existsByAddress(correspondenceAddress);
        if (!correspondenceAddressExist) {
            throw new InvalidAddressException(INVALID.getMessage() + ADDRESS.getMessage());
        }
    }
}

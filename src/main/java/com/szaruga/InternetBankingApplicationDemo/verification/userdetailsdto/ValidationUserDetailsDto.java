package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Utility class for validating UserDetailsDto objects.
 */
@Component
public class ValidationUserDetailsDto {
    @Autowired
    protected FieldCheckAddress fieldCheckAddress;
    @Autowired
    protected FieldCheckCorrespondenceAddress fieldCheckCorrespondenceAddress;
    @Autowired
    protected FieldCheckPostCode fieldCheckPostCode;
    @Autowired
    protected FieldCheckCity fieldCheckCity;
    @Autowired
    protected FiledCheckVoivodeship filedCheckVoivodeship;
    @Autowired
    protected FiledCheckCounty filedCheckCounty;

    /**
     * Validates the provided UserDetailsDto object.
     *
     * @param userDetailsDto The UserDetailsDto object to validate.
     */
    public void validateDto(UserDetailsDto userDetailsDto) {
        fieldCheckAddress.validate(userDetailsDto.getAddress());
        FieldCheckAddressHomeNumber.validate(userDetailsDto.getAddressHomeNumber());
        FiledCheckAddressFlatNumber.validate(userDetailsDto.getAddressFlatNumber());
        fieldCheckCorrespondenceAddress.validate(userDetailsDto.getCorrespondenceAddress());
        FieldCheckCorrespondenceAddressHomeNumber.validate(userDetailsDto.getCorrespondenceAddressHomeNumber());
        FieldCheckCorrespondenceAddressFlatNumber.validate(userDetailsDto.getCorrespondenceAddressFlatNumber());
        fieldCheckPostCode.validate(userDetailsDto.getPostCode());
        fieldCheckCity.validate(userDetailsDto.getCity());
        filedCheckVoivodeship.validate(userDetailsDto.getVoivodeship());
        filedCheckCounty.validate(userDetailsDto.getCounty());
    }
}
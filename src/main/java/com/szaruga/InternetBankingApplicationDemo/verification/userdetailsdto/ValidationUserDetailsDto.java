package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationUserDetailsDto {
    @Autowired
    protected FieldCheckAddress fieldCheckAddress;
    @Autowired
    protected FieldCheckAddressHomeNumber fieldCheckAddressHomeNumber;
    @Autowired
    protected FiledCheckAddressFlatNumber filedCheckAddressFlatNumber;
    @Autowired
    protected FieldCheckCorrespondenceAddress fieldCheckCorrespondenceAddress;
    @Autowired
    protected FieldCheckCorrespondenceAddressHomeNumber fieldCheckCorrespondenceAddressHomeNumber;

    @Autowired
    protected FieldCheckCorrespondenceAddressFlatNumber fieldCheckCorrespondenceAddressFlatNumber;

    @Autowired
    protected FieldCheckPostCode fieldCheckPostCode;
    @Autowired
    protected FieldCheckCity fieldCheckCity;


    public void validateDto(UserDetailsDto userDetailsDto) {
        fieldCheckAddress.validate(userDetailsDto.getAddress());
        fieldCheckAddressHomeNumber.validate(userDetailsDto.getAddressHomeNumber());
        filedCheckAddressFlatNumber.validate(userDetailsDto.getAddressFlatNumber());
        fieldCheckCorrespondenceAddress.validate(userDetailsDto.getCorrespondenceAddress());
        fieldCheckCorrespondenceAddressHomeNumber.validate(userDetailsDto.getCorrespondenceAddressHomeNumber());
        fieldCheckCorrespondenceAddressFlatNumber.validate(userDetailsDto.getCorrespondenceAddressFlatNumber());
        fieldCheckPostCode.validate(userDetailsDto.getPostCode());
        fieldCheckCity.validate(userDetailsDto.getCity());
    }
}

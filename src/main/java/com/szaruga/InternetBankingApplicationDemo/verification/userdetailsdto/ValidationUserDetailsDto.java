package com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UserDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class ValidationUserDetailsDto {
    public void validateDto(UserDetailsDto userDetailsDto){
        FieldCheckAddress.validate(userDetailsDto.getAddress());
        FieldCheckAddressHomeNumber.validate(userDetailsDto.getAddressHomeNumber());
        FiledCheckAddressFlatNumber.validate(userDetailsDto.getAddressFlatNumber());
        FieldCheckCorrespondenceAddress.validate(userDetailsDto.getCorrespondenceAddress());
        FieldCheckCorrespondenceAddressHomeNumber.validate(userDetailsDto.getCorrespondenceAddressHomeNumber());
        FieldCheckCorrespondenceAddressFlatNumber.validate(userDetailsDto.getCorrespondenceAddressFlatNumber());
        FieldCheckPostCode.validate(userDetailsDto.getPostCode());
        FieldCheckCity.validate(userDetailsDto.getCity());
    }
}

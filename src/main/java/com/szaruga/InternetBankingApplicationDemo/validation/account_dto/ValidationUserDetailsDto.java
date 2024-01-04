package com.szaruga.InternetBankingApplicationDemo.validation.account_dto;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UserDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class ValidationUserDetailsDto {
    public void validateDto(UserDetailsDto userDetailsDto){
        ValidateAddress.validate(userDetailsDto.getAddress());
        ValidateCorrespondenceAddress.validate(userDetailsDto.getCorrespondenceAddress());
    }
}

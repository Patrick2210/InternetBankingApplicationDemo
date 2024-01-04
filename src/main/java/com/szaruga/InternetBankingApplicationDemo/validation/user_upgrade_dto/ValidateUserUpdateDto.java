package com.szaruga.InternetBankingApplicationDemo.validation.user_upgrade_dto;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class ValidateUserUpdateDto {
    public void validateUserUpdateDto(UserUpdateDto updateDto) {
        ValidateUpdateFirstName.validate(updateDto.getFirstName());
        ValidateUpdateLastName.validate(updateDto.getLastName());
        ValidateUpdatePhoneNumber.validate(updateDto.getPhoneNumber());
        ValidateUpdateEmail.validate(updateDto.getEmail());
    }
}

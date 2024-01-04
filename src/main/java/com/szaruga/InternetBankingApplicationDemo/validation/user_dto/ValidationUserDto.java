package com.szaruga.InternetBankingApplicationDemo.validation.user_dto;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class ValidationUserDto {
    public void validateDto(UserDto user) {
        ValidateFirstName.validate(user.getFirstName());
        ValidateLastName.validate(user.getLastName());
        ValidatePeselNumber.validate(user.getNumberPesel());
        ValidateBirthDate.validate(user.getBirthDate());
        ValidatePhoneNumber.validate(user.getPhoneNumber());
        ValidatePasswords.validate(user.getPassword(), user.getPasswordReset());
        ValidateEmail.validate(user.getEmail());
    }
}
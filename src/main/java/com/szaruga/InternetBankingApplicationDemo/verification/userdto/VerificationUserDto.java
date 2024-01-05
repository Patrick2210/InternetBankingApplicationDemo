package com.szaruga.InternetBankingApplicationDemo.verification.userdto;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class VerificationUserDto {
    public void userDto(UserDto user) {
        FieldCheckFirstName.validate(user.getFirstName());
        FieldCheckLastName.validate(user.getLastName());
        FieldCheckPeselNumber.validate(user.getNumberPesel());
        FieldCheckBirthDate.validate(user.getBirthDate());
        FieldCheckPhoneNumber.validate(user.getPhoneNumber());
        FieldsCheckPasswords.validate(user.getPassword(), user.getRepeatPassword());
        FieldCheckEmail.validate(user.getEmail());
    }
}
package com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class VerificationUserUpdateDto {
    public void userUpdateDto(UserUpdateDto updateDto) {
        FieldCheckFirstNameUpdate.validate(updateDto.getFirstName());
        FieldCheckLastNameUpdate.validate(updateDto.getLastName());
        FieldCheckPhoneNumberUpdate.validate(updateDto.getPhoneNumber());
        FieldCheckEmailUpdate.validate(updateDto.getEmail());
    }
}

package com.szaruga.InternetBankingApplicationDemo.verification.user_password_update_dto;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserPasswordUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class VerificationUserPasswordUpdateDto {

    public static void userPasswordUpdateDto(UserPasswordUpdateDto passwordUpdateDto){
        FieldsCheckPasswordUpdate.validate(passwordUpdateDto.getPassword(), passwordUpdateDto.getRepeatPassword());
    }
}

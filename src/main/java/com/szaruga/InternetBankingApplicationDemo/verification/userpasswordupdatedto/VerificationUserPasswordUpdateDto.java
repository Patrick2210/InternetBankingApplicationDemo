package com.szaruga.InternetBankingApplicationDemo.verification.userpasswordupdatedto;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserPasswordUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class VerificationUserPasswordUpdateDto {

    public void userPasswordUpdateDto(UserPasswordUpdateDto passwordUpdateDto){
        FieldsCheckPasswordUpdate.validate(passwordUpdateDto.getPassword(), passwordUpdateDto.getRepeatPassword());
    }
}

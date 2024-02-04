package com.szaruga.InternetBankingApplicationDemo.verification.useremailupdatedto;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserEmailUpdateDto;
import org.springframework.stereotype.Component;


@Component
public class VerificationEmailUpdateDto {

    public void verificationEmailUpdateDto(UserEmailUpdateDto userEmailUpdateDto) {
        FieldsCheckEmailUpdate.validate(userEmailUpdateDto.getEmail());
    }
}
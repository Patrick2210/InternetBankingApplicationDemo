package com.szaruga.InternetBankingApplicationDemo.verification.useremailupdatedto;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserEmailUpdateDto;
import org.springframework.stereotype.Component;
/**
 * Utility class for verifying and validating user email update DTO objects.
 */
@Component
public class VerificationEmailUpdateDto {
    /**
     * Validates the provided UserEmailUpdateDto object.
     *
     * @param userEmailUpdateDto The UserEmailUpdateDto object to validate.
     */
    public void verificationEmailUpdateDto(UserEmailUpdateDto userEmailUpdateDto) {
        FieldsCheckEmailUpdate.validate(userEmailUpdateDto.getEmail());
    }
}
package com.szaruga.InternetBankingApplicationDemo.verification.userpasswordupdatedto;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserPasswordUpdateDto;
import org.springframework.stereotype.Component;
/**
 * Utility class for verifying and validating user password update DTO objects.
 */
@Component
public class VerificationUserPasswordUpdateDto {
    /**
     * Validates the provided UserPasswordUpdateDto object.
     *
     * @param passwordUpdateDto The UserPasswordUpdateDto object to validate.
     */
    public void userPasswordUpdateDto(UserPasswordUpdateDto passwordUpdateDto){
        FieldsCheckPasswordUpdate.validate(passwordUpdateDto.getPassword(), passwordUpdateDto.getRepeatPassword());
    }
}

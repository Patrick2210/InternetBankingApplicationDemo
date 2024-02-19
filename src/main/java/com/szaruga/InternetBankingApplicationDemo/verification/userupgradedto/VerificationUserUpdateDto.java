package com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserUpdateDto;
import org.springframework.stereotype.Component;

/**
 * Utility class for verifying and validating user update DTO objects.
 */
@Component
public class VerificationUserUpdateDto {
    /**
     * Validates the provided UserUpdateDto object.
     *
     * @param updateDto The UserUpdateDto object to validate.
     */
    public void userUpdateDto(UserUpdateDto updateDto) {
        FieldCheckFirstNameUpdate.validate(updateDto.getFirstName());
        FieldCheckLastNameUpdate.validate(updateDto.getLastName());
        FieldCheckPhoneNumberUpdate.validate(updateDto.getPhoneNumber());
        FieldCheckEmailUpdate.validate(updateDto.getEmail());
    }
}

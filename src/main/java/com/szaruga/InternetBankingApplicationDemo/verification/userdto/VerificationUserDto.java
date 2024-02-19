package com.szaruga.InternetBankingApplicationDemo.verification.userdto;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import org.springframework.stereotype.Component;

/**
 * Utility class for verifying and validating user DTO objects.
 */
@Component
public class VerificationUserDto {
    /**
     * Validates the provided UserDto object.
     *
     * @param user The UserDto object to validate.
     */
    public void userDto(UserDto user) {
        FieldCheckFirstName.validate(user.getFirstName());
        FieldCheckLastName.validate(user.getLastName());
        FieldCheckBirthDate.validate(user.getBirthDate());
        FieldCheckPhoneNumber.validate(user.getPhoneNumber());
        FieldsCheckPasswords.validate(user.getPassword(), user.getRepeatPassword());
        FieldCheckEmail.validate(user.getEmail());
    }
}
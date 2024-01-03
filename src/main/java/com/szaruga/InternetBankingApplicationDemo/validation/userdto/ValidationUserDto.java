package com.szaruga.InternetBankingApplicationDemo.validation.userdto;

import com.szaruga.InternetBankingApplicationDemo.dto.UserDto;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Component
public class ValidationUserDto {
    //TODO przeprowadzic reszte validacji
    public void validateDto(UserDto user) {
        ValidateFirstName.validate(user.getFirstName());
        ValidateLastName.validate(user.getLastName());
        ValidatePeselNumber.validate(user.getNumberPesel());
        ValidateBirthDate.validate(user.getBirthDate());
        ValidatePhoneNumber.validate(user.getPhoneNumber());
        validatePassword(user.getPassword());
        validatePasswordReset(user.getPasswordReset());
        validateEmail(user.getEmail());
    }



    public void validatePassword(String password) {
        if (password == null) {
            throw new ValidationException(PASSWORD.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }

    private void validatePasswordReset(String passwordReset) {
        if (passwordReset == null) {
            throw new ValidationException(PASSWORD_RESET.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }

    public void validateEmail(String email) {
        if (email == null) {
            throw new ValidationException(EMAIL.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }
}
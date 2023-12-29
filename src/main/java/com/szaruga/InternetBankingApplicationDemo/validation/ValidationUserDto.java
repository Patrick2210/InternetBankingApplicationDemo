package com.szaruga.InternetBankingApplicationDemo.validation;

import com.szaruga.InternetBankingApplicationDemo.dto.UserDto;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Component
public class ValidationUserDto {
//TODO przeprowadzic reszte validacji
    public void validateDto(UserDto user) {
        validateFirstName(user.getFirstName());
        validateLastName(user.getLastName());
        validatePeselNumber(user.getNumberPesel());
        validateBirthDate(user.getBirthDate());
        validatePhoneNumber(user.getPhoneNumber());
        validatePassword(user.getPassword());
        validateEmail(user.getEmail());
    }

    public void validateFirstName(String firstName) {
        if (firstName == null) {
            throw new ValidationException(FIRST_NAME.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }

    public void validateLastName(String lastName) {
        if (lastName == null) {
            throw new ValidationException(LAST_NAME.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }

    public void validatePeselNumber(String peselNumber) {
        if (peselNumber == null) {
            throw new ValidationException(PESEL_NUMBER.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }

    public void validateBirthDate(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minBirthDate = currentDate.minusYears(18);
        if (birthDate == null) {
            throw new ValidationException(BIRTH_DATE.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
        if (birthDate.isAfter(minBirthDate)) {
            throw new ValidationException(BIRTH_DATE.getMessage() + MINIMUM_18_YEARS_OLD.getMessage());
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            throw new ValidationException(PHONE_NUMBER.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }

    public void validatePassword(String password) {
        if (password == null) {
            throw new ValidationException(PASSWORD.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }

    public void validateEmail(String email) {
        if (email == null) {
            throw new ValidationException(EMAIL.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }
}
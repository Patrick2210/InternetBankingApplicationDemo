package com.szaruga.InternetBankingApplicationDemo.validation;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Component
public class ValidationDto {

    public String validateFirstName(String firstName) {
        if (firstName == null) {
            throw new ValidationException(FIRST_NAME.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
        return firstName;
    }

    public String validateLastName(String lastName) {
        if (lastName == null) {
            throw new ValidationException(LAST_NAME.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
        return lastName;
    }

    public String validatePeselNumber(String peselNumber) {
        if (peselNumber == null) {
            throw new ValidationException(PESEL_NUMBER.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
        return peselNumber;
    }

    public LocalDate validateBirthDate(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minBirthDate = currentDate.minusYears(18);
        if (birthDate == null) {
            throw new ValidationException(BIRTH_DATE.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
        if (birthDate.isAfter(minBirthDate)) {
            throw new ValidationException(BIRTH_DATE.getMessage() + MINIMUM_18_YEARS_OLD.getMessage());
        } else return birthDate;
    }

    public String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            throw new ValidationException(PHONE_NUMBER.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
        return phoneNumber;
    }

    public String validatePassword(String password) {
        if (password == null) {
            throw new ValidationException(PASSWORD.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
        return password;
    }

    public String validateEmail(String email) {
        if (email == null) {
            throw new ValidationException(EMAIL.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
        return email;
    }
}
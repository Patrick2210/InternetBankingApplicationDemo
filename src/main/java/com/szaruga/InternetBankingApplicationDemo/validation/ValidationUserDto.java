package com.szaruga.InternetBankingApplicationDemo.validation;

import com.szaruga.InternetBankingApplicationDemo.entity.User;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Component
public class ValidationUserDto {

    public User validateDto(User user) {
        User userValidate = new User();

        Optional.ofNullable(user.getFirstName())
                .map(this::validateFirstName)
                .ifPresent(userValidate::setFirstName);

        Optional.ofNullable(user.getLastName())
                .map(this::validateLastName)
                .ifPresent(userValidate::setLastName);

        Optional.ofNullable(user.getNumberPesel())
                .map(this::validatePeselNumber)
                .ifPresent(userValidate::setNumberPesel);

        Optional.ofNullable(user.getBirthDate())
                .map(this::validateBirthDate)
                .ifPresent(userValidate::setBirthDate);

        Optional.ofNullable(user.getPhoneNumber())
                .map(this::validatePhoneNumber)
                .ifPresent(userValidate::setPhoneNumber);

        Optional.ofNullable(user.getPassword())
                .map(this::validatePassword)
                .ifPresent(userValidate::setPassword);

        Optional.ofNullable(user.getEmail())
                .map(this::validateEmail)
                .ifPresent(userValidate::setEmail);

        return userValidate;
    }

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
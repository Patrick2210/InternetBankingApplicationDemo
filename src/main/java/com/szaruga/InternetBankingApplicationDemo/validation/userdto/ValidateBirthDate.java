package com.szaruga.InternetBankingApplicationDemo.validation.userdto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import java.time.LocalDate;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

public class ValidateBirthDate {

    public static void validate(LocalDate birthDate) {
        checkNotNull(birthDate);
        checkBirthDate(birthDate);
    }

    private static void checkNotNull(LocalDate birthDate) {
        if (birthDate == null) {
            throw new ValidationException(BIRTH_DATE.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }

    private static void checkBirthDate(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minBirthDate = currentDate.minusYears(18);
        if (birthDate.isAfter(minBirthDate)) {
            throw new ValidationException(BIRTH_DATE.getMessage() + MINIMUM_18_YEARS_OLD.getMessage());
        }
    }
}
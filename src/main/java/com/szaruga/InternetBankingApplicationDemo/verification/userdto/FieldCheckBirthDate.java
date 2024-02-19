package com.szaruga.InternetBankingApplicationDemo.verification.userdto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import java.time.LocalDate;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Verification class for user validation birthdate.
 */
public class FieldCheckBirthDate {
    /**
     * Validates the provided birthdate.
     *
     * @param birthDate The birthdate to validate.
     * @throws ValidationException If the birthdate is null or the user is under 18 years old.
     */
    public static void validate(LocalDate birthDate) {
        checkNotNull(birthDate);
        checkBirthDate(birthDate);
    }
    /**
     * Checks if the birthdate is null.
     *
     * @param birthDate The birthdate to check.
     * @throws ValidationException If the birthdate is null.
     */
    private static void checkNotNull(LocalDate birthDate) {
        if (birthDate == null) {
            throw new ValidationException(BIRTH_DATE.getMessage() + MUST_BE_NOT_NULL.getMessage());
        }
    }
    /**
     * Checks if the user is at least 18 years old based on the birthdate.
     *
     * @param birthDate The birthdate to check.
     * @throws ValidationException If the user is under 18 years old.
     */
    private static void checkBirthDate(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minBirthDate = currentDate.minusYears(18);
        if (birthDate.isAfter(minBirthDate)) {
            throw new ValidationException(BIRTH_DATE.getMessage() + MINIMUM_18_YEARS_OLD.getMessage());
        }
    }
}
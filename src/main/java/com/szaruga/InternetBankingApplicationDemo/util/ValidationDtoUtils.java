package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

/**
 * Utility class for validating DTO (Data Transfer Object) fields.
 */
public class ValidationDtoUtils {
    /**
     * Checks if the provided value is not null.
     *
     * @param value   The value to check.
     * @param message The error message to include in the exception if the value is null.
     * @throws ValidationException If the value is null.
     */
    public static void checkNotNull(String value, String message) {
        if (value == null) {
            throw new ValidationException(message + MUST_BE_NOT_NULL.getMessage());
        }
    }

    /**
     * Checks if the provided value is not empty.
     *
     * @param value   The value to check.
     * @param message The error message to include in the exception if the value is empty.
     * @throws ValidationException If the value is empty.
     */
    public static void checkNotEmpty(String value, String message) {
        if (value.isEmpty()) {
            throw new ValidationException(message + MUST_BE_NOT_EMPTY.getMessage());
        }
    }

    /**
     * Checks if the length of the provided value is less than a minimum length.
     *
     * @param value   The value to check.
     * @param message The error message to include in the exception if the value is too short.
     * @throws ValidationException If the length of the value is less than the minimum.
     */
    public static void checkMinSize(String value, String message) {
        int lengthChar = 3;
        if (value.length() < lengthChar) {
            throw new ValidationException(message + MIN_CHAR.getMessage() + lengthChar + CHARACTERS.getMessage());
        }
    }

    /**
     * Checks if the length of the provided value is greater than a maximum length.
     *
     * @param value   The value to check.
     * @param message The error message to include in the exception if the value is too long.
     * @throws ValidationException If the length of the value exceeds the maximum.
     */
    public static void checkMaxSize(String value, String message) {
        int lengthChar = 35;
        if (value.length() > lengthChar) {
            throw new ValidationException(message + MAX_CHAR.getMessage() + lengthChar + CHARACTERS.getMessage());
        }
    }

    /**
     * Checks if the provided value contains only letters (no special characters or digits).
     *
     * @param value   The value to check.
     * @param message The error message to include in the exception if the value contains special characters.
     * @throws ValidationException If the value contains special characters.
     */
    public static void checkSpecialCharacters(String value, String message) {
        if (!value.matches("^[a-zA-Z]+$")) {
            throw new ValidationException(message + SPECIAL_CHARACTERS.getMessage());
        }
    }

    /**
     * Checks if the provided value contains only letters and digits (no special characters).
     *
     * @param value   The value to check.
     * @param message The error message to include in the exception if the value contains special characters.
     * @throws ValidationException If the value contains special characters.
     */
    public static void checkIfContainsSpecialCharacters(String value, String message) {
        if (!value.matches("^[a-zA-Z0-9]+$")) {
            throw new ValidationException(message + LETTERS_AND_NUMBERS_NOT_SPECIAL_CHARACTERS.getMessage());
        }
    }

    /**
     * Checks if the provided phone number is in a valid format.
     *
     * @param phoneNumber The phone number to check.
     * @param message     The error message to include in the exception if the phone number is invalid.
     * @throws ValidationException If the phone number is not in a valid format.
     */
    public static void checkFormatNumber(String phoneNumber, String message) {
        if (!(phoneNumber.matches("^\\+48\\d{9}$") || phoneNumber.matches("^\\d{9}$"))) {
            throw new ValidationException(message + PHONE_NUMBER_FORMAT.getMessage());
        }
    }

    /**
     * Checks if the provided email address is in a valid format.
     *
     * @param email   The email address to check.
     * @param message The error message to include in the exception if the email address is invalid.
     * @throws ValidationException If the email address is not in a valid format.
     */
    public static void isValidEmail(String email, String message) {
        if (!email.contains("@") || !email.contains(".")) {
            throw new ValidationException(message + INVALID.getMessage());
        }
    }

    /**
     * Checks if the provided password meets complexity requirements.
     *
     * @param password      The password to check.
     * @param passwordReset The password to compare with the original.
     * @throws ValidationException If the password does not meet complexity requirements.
     */
    public static void isPasswordComplex(String password, String passwordReset) {
        arePasswordsEqual(password, passwordReset);
        if (!password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&£])[A-Za-z\\d@$!%*?&£]+$")) {
            throw new ValidationException(PASSWORD_REQUIREMENTS.getMessage());
        }
    }

    /**
     * Checks if two passwords are equal.
     *
     * @param password      The original password.
     * @param passwordReset The password to compare with the original.
     * @throws ValidationException If the two passwords do not match.
     */
    private static void arePasswordsEqual(String password, String passwordReset) {
        if (!passwordReset.equals(password)) {
            throw new ValidationException(PASSWORD_DO_NOT_MATCH.getMessage());
        }
    }
}
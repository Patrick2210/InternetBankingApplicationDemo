package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

public class ValidationUserDtoUtils {

    public static void checkNotNull(String value, String message) {
        if (value == null) {
            throw new ValidationException(message + MUST_BE_NOT_NULL.getMessage());
        }
    }

    public static void checkNotEmpty(String value, String message) {
        if (value.isEmpty()) {
            throw new ValidationException(message + MUST_BE_NOT_EMPTY.getMessage());
        }
    }

    public static void checkMinSize(String value, String message) {
        int lengthChar = 3;
        if (value.length() < lengthChar) {
            throw new ValidationException(message + MIN_CHAR.getMessage() + lengthChar + CHARACTERS.getMessage());
        }
    }

    public static void checkMaxSize(String value, String message) {
        int lengthChar = 35;
        if (value.length() > lengthChar) {
            throw new ValidationException(message + MAX_CHAR.getMessage() + lengthChar + CHARACTERS.getMessage());
        }
    }

    public static void checkSpecialCharacters(String value, String message) {
        if (!value.matches("^[a-zA-Z]+$")) {
            throw new ValidationException(message + SPECIAL_CHARACTERS.getMessage());
        }
    }

    public static void checkFormatNumber(String phoneNumber, String message) {
        if (!(phoneNumber.matches("^\\+48\\d{9}$") || phoneNumber.matches("^\\d{9}$"))) {
            throw new ValidationException(message + PHONE_NUMBER_FORMAT.getMessage());
        }
    }

    private static boolean containsUnicodeChar(String text, int uniCodeTable) {
        for (int i = 0; i < text.length(); i++) {
            if (text.codePointAt(i) == uniCodeTable) {
                return false;
            }
        }
        return true;
    }

    public static void isValidEmail(String email, String message) {
        if (containsUnicodeChar(email, 64) || containsUnicodeChar(email, 46)) {
            throw new ValidationException(message + INVALID.getMessage());
        }
    }

    private static void arePasswordsEqual(String password, String passwordReset) {
        if (!passwordReset.equals(password)) {
            throw new ValidationException(PASSWORD_DO_NOT_MATCH.getMessage());
        }
    }

    public static void isPasswordComplex(String password, String passwordReset) {
        arePasswordsEqual(password, passwordReset);
        if (!password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&£])[A-Za-z\\d@$!%*?&£]+$")) {
            throw new ValidationException(PASSWORD_REQUIREMENTS.getMessage());
        }
    }
}
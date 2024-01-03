package com.szaruga.InternetBankingApplicationDemo.validation.userdto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationUserDtoUtils.*;

public class ValidateEmail {

    public static void validate(String email) {
        checkNotNull(email, EMAIL.getMessage());
        checkNotEmpty(email, EMAIL.getMessage());
        isValidEmail(email);
    }
    private static boolean containsUnicodeChar(String text, int uniCodeTable) {
        for (int i = 0; i < text.length(); i++) {
            if (text.codePointAt(i) == uniCodeTable) {
                return false;
            }
        }
        return true;
    }

    private static void isValidEmail(String email) {
        if (containsUnicodeChar(email, 64) || containsUnicodeChar(email, 46)) {
            throw new ValidationException(EMAIL.getMessage() + INVALID.getMessage());
        }
    }
}

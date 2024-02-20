package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;
import org.junit.jupiter.api.Test;

import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.checkMinSize;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationDtoUtilsTest {
    private final String testMessage = "testMessage";

    @Test
    public void testCheckNotNull_ValueIsNotNull() {
        String testValue = "testValue";
        assertDoesNotThrow(() -> checkNotNull(testValue, testMessage));
    }

    @Test
    public void testCheckNotNull_ValueIsNull() {
        assertThrows(ValidationException.class, () -> checkNotNull(null, testMessage));
    }

    @Test
    public void testCheckNotEmpty_ValueIsNotEmpty() {
        String testValue = "testValue";
        assertDoesNotThrow(() -> checkNotEmpty(testValue, testMessage));
    }

    @Test
    public void testCheckNotEmpty_ValueIsEmpty() {
        assertThrows(ValidationException.class, () -> checkNotEmpty("", testMessage));
    }

    @Test
    public void testCheckMinSize_Value_ThrowsException() {
        String valueOneCharacterLength = "a";
        assertThrows(ValidationException.class, () -> checkMinSize(valueOneCharacterLength, testMessage));
    }

    @Test
    public void testCheckMinSize_Value_DoesNotThrowException() {
        String valueThreeCharLength = "abc";
        assertDoesNotThrow(() -> checkMinSize(valueThreeCharLength, testMessage));
    }

    @Test
    public void testCheckMaxSize_Value_ThrowsException() {
        String valueThirtyFiveCharacterLength = "abcabcabcabcabcabcabcabcabcabcabcabc";
        assertThrows(ValidationException.class, () -> checkMaxSize(valueThirtyFiveCharacterLength, testMessage));
    }

    @Test
    public void testCheckMaxSize_Value_DoesNotThrowException() {
        String twentyCharacterLength = "abcabcabcabcabcabcab";
        assertDoesNotThrow(() -> checkMaxSize(twentyCharacterLength, testMessage));
    }

    @Test
    public void testCheckSpecialCharacters_Value_ThrowsException() {
        String valueWithoutSpecialCharacters = "valueTest";
        assertDoesNotThrow(() -> checkSpecialCharacters(valueWithoutSpecialCharacters, testMessage));
    }

    @Test
    public void testCheckSpecialCharacters_Value_DoesNotThrowException() {
        String valueWithSpecialCharacters = "Test123";
        assertThrows(ValidationException.class, () -> checkSpecialCharacters(valueWithSpecialCharacters, testMessage));
    }

    @Test
    public void testCheckIfContainsSpecialCharacters_Value_ThrowsException() {
        String valueContainingSpecialCharacters = "Test@123";
        assertThrows(ValidationException.class, () -> checkIfContainsSpecialCharacters(valueContainingSpecialCharacters, testMessage));
    }

    @Test
    public void testCheckIfContainsSpecialCharacters_Value_DoesNotThrowException() {
        String valueContainingOnlyLettersAndNumbers = "Test123";
        assertDoesNotThrow(() -> checkIfContainsSpecialCharacters(valueContainingOnlyLettersAndNumbers, testMessage));
    }

    @Test
    public void testCheckFormatNumber_Value_ThrowsException() {
        String invalidPhoneNumber = "12345";
        assertThrows(ValidationException.class, () -> checkFormatNumber(invalidPhoneNumber, testMessage));
    }

    @Test
    public void testCheckFormatNumber_Value_DoesNotThrowException() {
        String validPhoneNumberWithCountryCode = "+48123456789";
        assertDoesNotThrow(() -> checkFormatNumber(validPhoneNumberWithCountryCode, testMessage));

        String validPhoneNumberWithoutCountryCode = "123456789";
        assertDoesNotThrow(() -> checkFormatNumber(validPhoneNumberWithoutCountryCode, testMessage));
    }

    @Test
    public void testIsValidEmail_Value_ThrowsException() {
        String emailWithoutAtSymbol = "testexample.com";
        assertThrows(ValidationException.class, () -> isValidEmail(emailWithoutAtSymbol, testMessage));

        String emailWithoutDot = "test@examplecom";
        assertThrows(ValidationException.class, () -> isValidEmail(emailWithoutDot, testMessage));
    }

    @Test
    public void testIsValidEmail_Value_DoesNotThrowException() {
        String validEmail = "test@example.com";
        assertDoesNotThrow(() -> isValidEmail(validEmail, testMessage));
    }

    @Test
    public void testIsPasswordComplex_Value_ThrowsException() {
        String passwordWithoutSpecialCharacters = "Password123";
        String repeatPasswordWithoutSpecialCharacters = "Password1123";
        assertThrows(ValidationException.class, () -> isPasswordComplex(passwordWithoutSpecialCharacters, repeatPasswordWithoutSpecialCharacters));
    }

    @Test
    public void testIsPasswordComplex_Value_DoesNotThrowException() {
        String complexPassword = "Passww0rd@";
        String repeatComplexPassword = "Passww0rd@";
        assertDoesNotThrow(() -> isPasswordComplex(complexPassword, repeatComplexPassword));
    }
}

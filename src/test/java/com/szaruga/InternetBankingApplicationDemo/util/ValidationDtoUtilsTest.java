package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;
import org.junit.jupiter.api.Test;

import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ValidationDtoUtils}.
 */
public class ValidationDtoUtilsTest {
    private final String testMessage = "testMessage";

    /**
     * Test case for {@link ValidationDtoUtils #checkNotNull(Object, String)} when value is not null.
     */
    @Test
    public void testCheckNotNull_ValueIsNotNull() {
        String testValue = "testValue";
        assertDoesNotThrow(() -> checkNotNull(testValue, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils #checkNotNull(Object, String)} when value is null.
     */
    @Test
    public void testCheckNotNull_ValueIsNull() {
        assertThrows(ValidationException.class, () -> checkNotNull(null, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils #checkNotEmpty(String, String)} when value is not empty.
     */
    @Test
    public void testCheckNotEmpty_ValueIsNotEmpty() {
        String testValue = "testValue";
        assertDoesNotThrow(() -> checkNotEmpty(testValue, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils #checkNotEmpty(String, String)} when value is empty.
     */
    @Test
    public void testCheckNotEmpty_ValueIsEmpty() {
        assertThrows(ValidationException.class, () -> checkNotEmpty("", testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#checkMinSize(String, String)} when value length is less than minimum.
     */
    @Test
    public void testCheckMinSize_Value_ThrowsException() {
        String valueOneCharacterLength = "a";
        assertThrows(ValidationException.class, () -> checkMinSize(valueOneCharacterLength, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#checkMinSize(String, String)} when minimum value length is correct.
     */
    @Test
    public void testCheckMinSize_Value_DoesNotThrowException() {
        String valueThreeCharLength = "abc";
        assertDoesNotThrow(() -> checkMinSize(valueThreeCharLength, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#checkMaxSize(String, String)} when value length is greater than maximum.
     */
    @Test
    public void testCheckMaxSize_Value_ThrowsException() {
        String valueThirtyFiveCharacterLength = "abcabcabcabcabcabcabcabcabcabcabcabc";
        assertThrows(ValidationException.class, () -> checkMaxSize(valueThirtyFiveCharacterLength, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#checkMaxSize(String, String)} when maximum value length is correct.
     */
    @Test
    public void testCheckMaxSize_Value_DoesNotThrowException() {
        String twentyCharacterLength = "abcabcabcabcabcabcab";
        assertDoesNotThrow(() -> checkMaxSize(twentyCharacterLength, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#checkSpecialCharacters(String, String)} when value contains no special characters.
     */
    @Test
    public void testCheckSpecialCharacters_Value_ThrowsException() {
        String valueWithoutSpecialCharacters = "valueTest";
        assertDoesNotThrow(() -> checkSpecialCharacters(valueWithoutSpecialCharacters, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#checkSpecialCharacters(String, String)} when value contains special characters.
     */
    @Test
    public void testCheckSpecialCharacters_Value_DoesNotThrowException() {
        String valueWithSpecialCharacters = "Test123";
        assertThrows(ValidationException.class, () -> checkSpecialCharacters(valueWithSpecialCharacters, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#checkIfContainsSpecialCharacters(String, String)} when value contains special characters.
     */
    @Test
    public void testCheckIfContainsSpecialCharacters_Value_ThrowsException() {
        String valueContainingSpecialCharacters = "Test@123";
        assertThrows(ValidationException.class, () -> checkIfContainsSpecialCharacters(valueContainingSpecialCharacters, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#checkIfContainsSpecialCharacters(String, String)} when value contains only letters and numbers.
     */
    @Test
    public void testCheckIfContainsSpecialCharacters_Value_DoesNotThrowException() {
        String valueContainingOnlyLettersAndNumbers = "Test123";
        assertDoesNotThrow(() -> checkIfContainsSpecialCharacters(valueContainingOnlyLettersAndNumbers, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#checkFormatNumber(String, String)} when value has invalid format.
     */
    @Test
    public void testCheckFormatNumber_Value_ThrowsException() {
        String invalidPhoneNumber = "12345";
        assertThrows(ValidationException.class, () -> checkFormatNumber(invalidPhoneNumber, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#checkFormatNumber(String, String)} when value has valid format.
     */
    @Test
    public void testCheckFormatNumber_Value_DoesNotThrowException() {
        String validPhoneNumberWithCountryCode = "+48123456789";
        assertDoesNotThrow(() -> checkFormatNumber(validPhoneNumberWithCountryCode, testMessage));

        String validPhoneNumberWithoutCountryCode = "123456789";
        assertDoesNotThrow(() -> checkFormatNumber(validPhoneNumberWithoutCountryCode, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#isValidEmail(String, String)} when email format is invalid.
     */
    @Test
    public void testIsValidEmail_Value_ThrowsException() {
        String emailWithoutAtSymbol = "testexample.com";
        assertThrows(ValidationException.class, () -> isValidEmail(emailWithoutAtSymbol, testMessage));

        String emailWithoutDotSymbol = "test@examplecom";
        assertThrows(ValidationException.class, () -> isValidEmail(emailWithoutDotSymbol, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#isValidEmail(String, String)} when email format is valid.
     */
    @Test
    public void testIsValidEmail_Value_DoesNotThrowException() {
        String validEmail = "test@example.com";
        assertDoesNotThrow(() -> isValidEmail(validEmail, testMessage));
    }

    /**
     * Test case for {@link ValidationDtoUtils#isPasswordComplex(String, String)} when password is not complex.
     */
    @Test
    public void testIsPasswordComplex_Value_ThrowsException() {
        String passwordWithoutSpecialCharacters = "Password123";
        String repeatPasswordWithoutSpecialCharacters = "Password1123";
        assertThrows(ValidationException.class, () -> isPasswordComplex(passwordWithoutSpecialCharacters, repeatPasswordWithoutSpecialCharacters));
    }

    /**
     * Test case for {@link ValidationDtoUtils#isPasswordComplex(String, String)} when password is complex.
     */
    @Test
    public void testIsPasswordComplex_Value_DoesNotThrowException() {
        String complexPassword = "Passww0rd@";
        String repeatComplexPassword = "Passww0rd@";
        assertDoesNotThrow(() -> isPasswordComplex(complexPassword, repeatComplexPassword));
    }
}
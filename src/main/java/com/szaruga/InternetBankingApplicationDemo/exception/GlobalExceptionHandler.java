package com.szaruga.InternetBankingApplicationDemo.exception;

import com.szaruga.InternetBankingApplicationDemo.exception.account.*;
import com.szaruga.InternetBankingApplicationDemo.exception.address.*;
import com.szaruga.InternetBankingApplicationDemo.exception.user.*;
import com.szaruga.InternetBankingApplicationDemo.exception.userdetails.*;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the Internet Banking Application.
 * Handles specific exceptions and returns appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles AccountNotFoundException and returns a response with status 404 (NOT_FOUND).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(AccountNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles InsufficientBalanceException and returns a response with status 404 (NOT_FOUND).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Object> handleInsufficientBalanceException(InsufficientBalanceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles CsvFileException and returns a response with status 404 (NOT_FOUND).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(CsvFileException.class)
    public ResponseEntity<Object> handleAddressCsvFileException(CsvFileException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    /**
     * Handles InvalidAddressException and returns a response with status 404 (NOT_FOUND).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(InvalidAddressException.class)
    public ResponseEntity<Object> handleInvalidAddressException(InvalidAddressException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    /**
     * Handles InvalidCityException and returns a response with status 404 (NOT_FOUND).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(InvalidCityException.class)
    public ResponseEntity<Object> handleInvalidCityException(InvalidCityException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    /**
     * Handles InvalidPostcodeException and returns a response with status 404 (NOT_FOUND).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(InvalidPostcodeException.class)
    public ResponseEntity<Object> handleInvalidPostcodeException(InvalidPostcodeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    /**
     * Handles PeselValidationException and returns a response with status 400 (BAD_REQUEST).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(PeselValidationException.class)
    public ResponseEntity<Object> handlePeselValidationException(PeselValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles UserHasAccountsException and returns a response with status 404 (NOT_FOUND).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(UserHasAccountsException.class)
    public ResponseEntity<Object> handleUserHasAccountsException(UserHasAccountsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles UserNotFoundException and returns a response with status 404 (NOT_FOUND).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles UserDetailsNotFoundException and returns a response with status 404 (NOT_FOUND).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(UserDetailsNotFoundException.class)
    public ResponseEntity<Object> handleUserDetailsNotFoundException(UserDetailsNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles IllegalSortingRequest and returns a response with status 404 (NOT_FOUND).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(IllegalSortingRequest.class)
    public ResponseEntity<Object> handleIllegalSortingRequest(IllegalSortingRequest ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    /**
     * Handles ValidationException and returns a response with status 404 (NOT_FOUND).
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing the error message.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
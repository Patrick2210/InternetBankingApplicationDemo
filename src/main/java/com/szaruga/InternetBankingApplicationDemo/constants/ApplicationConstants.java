package com.szaruga.InternetBankingApplicationDemo.constants;

public enum ApplicationConstants {
    FIRST_NAME("firstname "),
    LAST_NAME("lastname "),
    BIRTH_DATE("birthday date "),
    PESEL_NUMBER("pesel number "),
    PHONE_NUMBER("phone number "),
    PASSWORD("password "),
    PASSWORD_RESET("password reset "),
    EMAIL("email "),
    MINIMUM_18_YEARS_OLD("must be at least 18 years old"),
    MUST_BE_NOT_NULL("must be not null"),
    MUST_BE_NOT_EMPTY("must be not empty"),
    MIN_CHAR("must have at least "),
    MAX_CHAR("can't be longer than "),
    INVALID("invalid"),
    CHARACTERS(" characters "),
    PASSWORD_REQUIREMENTS("password must contains at least one letter, one digital number and one special character"),
    PASSWORD_DO_NOT_MATCH("passwords do not match"),
    PHONE_NUMBER_FORMAT(" should start with '+48' followed by a 9-digit number, or be a standalone 9-digit number "),
    PESEL_NUMBER_LENGTH(" length is 11 dig number "),
    SPECIAL_CHARACTERS("must contain only letters, special characters not allowed"),
    ACCOUNT_NOT_FOUND_WITH_ID("ACCOUNT NOT FOUND WITH ID: "),
    USER_DETAILS_NOT_FOUND_WITH_ID("USER DETAILS NOT FOUND WITH ID: "),
    USER_NOT_FOUND_WITH_ID("USER NOT FOUND WITH ID: ");

    private final String message;

    ApplicationConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

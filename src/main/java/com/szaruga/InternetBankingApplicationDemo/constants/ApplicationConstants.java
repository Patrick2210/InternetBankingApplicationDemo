package com.szaruga.InternetBankingApplicationDemo.constants;

public enum ApplicationConstants {
    ID("id"),
    FIRST_NAME("firstname "),
    LAST_NAME("lastname "),
    BIRTH_DATE("birth date "),
    PESEL_NUMBER("pesel number "),
    PHONE_NUMBER("phone number "),
    PASSWORD("password "),
    ACCOUNT_TYPE("account type "),
    BALANCE("balance"),
    REFERENCE_ACCOUNT_NUMBER("reference account number "),
    INVALID_SORT_FIELD("Invalid sort field: "),
    SORTING_ID("id"),
    SORTING_FIRSTNAME("firstName"),
    SORTING_LASTNAME("lastName"),
    SORTING_BIRTHDATE("birthDate"),
    SORTING_REFERENCE_ACCOUNT_NUMBER("referenceAccountNumber"),
    SORTING_POSTCODE("postCode"),
    SORTING_CITY("city"),
    PASSWORD_REPEAT("password repeat "),
    EMAIL("email "),
    MINIMUM_18_YEARS_OLD("must be at least 18 years old"),
    MUST_BE_NOT_NULL("must be not null"),
    MUST_BE_NOT_EMPTY("must be not empty"),
    MIN_CHAR("must have at least "),
    MAX_CHAR("can't be longer than "),
    INVALID("invalid"),
    CHARACTERS(" characters "),
    NAMING_ALREADY_EXIST("with this naming already exist, please choose another name for account"),
    INSUFFICIENT_BALANCE("Insufficient balance for withdrawal"),
    ACCOUNT_DELETE("before close account make sure balance is equal 0"),
    PASSWORD_REQUIREMENTS("password must contains at least one letter, one digital number and one special character"),
    PASSWORD_DO_NOT_MATCH("passwords do not match"),
    PHONE_NUMBER_FORMAT(" should start with '+48' followed by a 9-digit number, or be a standalone 9-digit number "),
    PESEL_NUMBER_LENGTH(" length is 11 dig number "),
    SPECIAL_CHARACTERS("must contain only letters, special characters not allowed"),
    ACCOUNT_NOT_FOUND_WITH_ID("ACCOUNT NOT FOUND WITH ID: "),
    USER_DETAILS_NOT_FOUND_WITH_ID("USER DETAILS NOT FOUND WITH ID: "),
    USER_HAS_ACCOUNT("User has open accounts and cannot be deleted"),
    USER_NOT_FOUND_WITH_ID("USER NOT FOUND WITH ID: ");

    private final String message;

    ApplicationConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

package com.szaruga.InternetBankingApplicationDemo.constants;
/**
 * Enumeration of application constants containing error messages.
 */
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
    ADDRESS("address "),
    CORRESPONDENCE_ADDRESS("correspondence address "),
    ADDRESS_HOME_NUMBER("address home number "),
    ADDRESS_FLAT_NUMBER("address flat number "),
    CORRESPONDENCE_ADDRESS_FLAT_NUMBER("correspondence address flat number "),
    CORRESPONDENCE_ADDRESS_HOUSE_NUMBER("correspondence address house number "),
    INVALID_SORT_FIELD("Invalid sort field: "),
    SORTING_ID("id"),
    SORTING_FIRSTNAME("firstName"),
    SORTING_LASTNAME("lastName"),
    SORTING_BIRTHDATE("birthDate"),
    SORTING_REFERENCE_ACCOUNT_NUMBER("referenceAccountNumber"),
    POSTCODE("postcode"),
    CITY("city"),
    PASSWORD_REPEAT("password repeat "),
    VOIVODESHIP("voivodeship"),
    COUNTY("county"),
    EMAIL("email "),
    MINIMUM_18_YEARS_OLD("must be at least 18 years old"),
    MUST_BE_NOT_NULL("must be not null"),
    MUST_BE_NOT_EMPTY("must be not empty"),
    MIN_CHAR("must have at least "),
    MAX_CHAR("can't be longer than "),
    INVALID("invalid "),
    CHARACTERS(" characters "),
    PESEL_INVALID_VALIDATION("Pesel validation failed"),
    NAMING_ALREADY_EXIST("with this naming already exist, please choose another name for account"),
    INSUFFICIENT_BALANCE("Insufficient balance for withdrawal"),
    ACCOUNT_DELETE("before close account make sure balance is equal 0"),
    PASSWORD_REQUIREMENTS("password must contains at least one letter, one digital number and one special character"),
    PASSWORD_DO_NOT_MATCH("passwords do not match"),
    PHONE_NUMBER_FORMAT(" should start with '+48' followed by a 9-digit number, or be a standalone 9-digit number "),
    SPECIAL_CHARACTERS("must contain only letters, special characters not allowed"),
    LETTERS_AND_NUMBERS_NOT_SPECIAL_CHARACTERS("must contain only letters and whole numbers, special characters not allowed"),
    ACCOUNT_NOT_FOUND_WITH_ID("ACCOUNT NOT FOUND WITH ID: "),
    USER_DETAILS_NOT_FOUND_WITH_ID("USER DETAILS NOT FOUND WITH ID: "),
    USER_HAS_ACCOUNT("User has open accounts and cannot be deleted"),
    USER_NOT_FOUND_WITH_ID("USER NOT FOUND WITH ID: "),
    CSV_FILE("CSV file "),
    IMPORT_SUCCESS("imported successfully!"),
    IMPORT_ERROR("Error importing ");

    private final String message;
    /**
     * Constructs an ApplicationConstants enum with the provided error message.
     *
     * @param message The error message associated with the constant.
     */
    ApplicationConstants(String message) {
        this.message = message;
    }
    /**
     * Gets the error message associated with the constant.
     *
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }
}

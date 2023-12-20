package com.szaruga.InternetBankingApplicationDemo.constants;

public enum ApplicationConstants {
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

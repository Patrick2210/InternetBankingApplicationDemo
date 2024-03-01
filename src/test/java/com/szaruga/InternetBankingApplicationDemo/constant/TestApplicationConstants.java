package com.szaruga.InternetBankingApplicationDemo.constant;
/**
 * Enumeration of test application constants containing error messages.
 */
public enum TestApplicationConstants {
    INVALID_SORT("invalidSortField");
    private final String testMessage;
    /**
     * Constructs an TestApplicationConstants enum with the provided error message.
     *
     * @param testMessage The error message associated with the constant.
     */
    TestApplicationConstants(String testMessage) {
        this.testMessage = testMessage;
    }

    /**
     * Gets the error message associated with the constant.
     *
     * @return The error message.
     */
    public String getMessage() {
        return testMessage;
    }
}

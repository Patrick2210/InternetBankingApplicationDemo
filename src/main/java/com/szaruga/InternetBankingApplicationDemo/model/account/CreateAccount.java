package com.szaruga.InternetBankingApplicationDemo.model.account;

/**
 * Represents the response model for creating a new account.
 */
public class CreateAccount {

    private final long clientAccountId;

    /**
     * Constructs a new CreateAccount instance with the provided client account ID.
     *
     * @param clientAccountId The ID of the newly created account.
     */
    public CreateAccount(long clientAccountId) {
        this.clientAccountId = clientAccountId;
    }

    /**
     * Retrieves the client account ID.
     *
     * @return The ID of the newly created account.
     */
    public long getClientAccountId() {
        return clientAccountId;
    }
}
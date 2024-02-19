package com.szaruga.InternetBankingApplicationDemo.model.user;

/**
 * Represents the response model for creating a new user.
 */
public class CreateUser {

    private final long clientId;

    /**
     * Constructs a new CreateUser instance with the provided client ID.
     *
     * @param clientId The ID of the newly created user.
     */
    public CreateUser(long clientId) {
        this.clientId = clientId;
    }

    /**
     * Retrieves the client ID.
     *
     * @return The ID of the newly created user.
     */
    public long getClientId() {
        return clientId;
    }
}
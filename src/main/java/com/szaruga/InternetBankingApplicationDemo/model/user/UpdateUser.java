package com.szaruga.InternetBankingApplicationDemo.model.user;

/**
 * Represents the response model for updating a user.
 */
public class UpdateUser {

    private final long clientId;

    /**
     * Constructs a new UpdateUser instance with the provided client ID.
     *
     * @param clientId The ID of the updated user.
     */
    public UpdateUser(long clientId) {
        this.clientId = clientId;
    }

    /**
     * Retrieves the client ID.
     *
     * @return The ID of the updated user.
     */
    public long getClientId() {
        return clientId;
    }
}
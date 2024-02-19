package com.szaruga.InternetBankingApplicationDemo.model.userdetails;

/**
 * Represents the response model for creating user details.
 */
public class CreateUserDetails {

    private final long clientUserDetailsId;

    /**
     * Constructs a new CreateUserDetails instance with the provided client ID.
     *
     * @param clientUserDetailsId The ID of the created user details.
     */
    public CreateUserDetails(int clientUserDetailsId) {
        this.clientUserDetailsId = clientUserDetailsId;
    }

    /**
     * Retrieves the client user details ID.
     *
     * @return The ID of the created user details.
     */
    public long getClientUserDetailsId() {
        return clientUserDetailsId;
    }
}
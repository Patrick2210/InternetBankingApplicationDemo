package com.szaruga.InternetBankingApplicationDemo.model.userdetails;

public class CreateUserDetails {

    private final long clientUserDetailsId;

    public CreateUserDetails(int clientId) {
        this.clientUserDetailsId = clientId;
    }

    public long getClientUserDetailsId() {
        return clientUserDetailsId;
    }
}
package com.szaruga.InternetBankingApplicationDemo.model.user;

public class UpdateUser {

    private final long clientId;

    public UpdateUser(long clientId) {
        this.clientId = clientId;
    }

    public long getClientId() {
        return clientId;
    }
}
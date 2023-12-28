package com.szaruga.InternetBankingApplicationDemo.model;

public class CreateUser {

    private final long clientId;

    public CreateUser(long clientId) {
        this.clientId = clientId;
    }

    public long getClientId() {
        return clientId;
    }
}
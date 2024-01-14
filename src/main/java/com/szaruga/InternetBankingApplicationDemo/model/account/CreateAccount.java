package com.szaruga.InternetBankingApplicationDemo.model.account;

public class CreateAccount {

    private final long clientAccountId;

    public CreateAccount(long clientAccountId) {
        this.clientAccountId = clientAccountId;
    }

    public long getClientAccountId() {
        return clientAccountId;
    }
}
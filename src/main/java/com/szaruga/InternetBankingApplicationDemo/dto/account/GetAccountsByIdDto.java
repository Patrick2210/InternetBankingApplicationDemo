package com.szaruga.InternetBankingApplicationDemo.dto.account;

import java.math.BigDecimal;

public class GetAccountsByIdDto {

    private String accountType;
    private int referenceAccountNumber;
    private BigDecimal balance;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getReferenceAccountNumber() {
        return referenceAccountNumber;
    }

    public void setReferenceAccountNumber(int referenceAccountNumber) {
        this.referenceAccountNumber = referenceAccountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

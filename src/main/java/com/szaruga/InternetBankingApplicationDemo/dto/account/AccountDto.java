package com.szaruga.InternetBankingApplicationDemo.dto.account;

import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;

import java.math.BigDecimal;

public class AccountDto {
    private String accountType;
    private int referenceAccountNumber;
    private BigDecimal balance;
    private UserEntity user;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

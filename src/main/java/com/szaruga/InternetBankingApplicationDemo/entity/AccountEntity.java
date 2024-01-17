package com.szaruga.InternetBankingApplicationDemo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "user_account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String accountType;
    private int referenceAccountNumber;
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public AccountEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "AccountEntity{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", referenceAccountNumber=" + referenceAccountNumber +
                ", balance=" + balance +
                '}';
    }
}

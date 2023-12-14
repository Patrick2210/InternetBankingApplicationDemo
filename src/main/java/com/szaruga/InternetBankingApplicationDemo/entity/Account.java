package com.szaruga.InternetBankingApplicationDemo.entity;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountType;
    private int referenceAccountNumber;
    private float balance;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
    private User user;

    public Account() {
    }

    public Account(int id, String accountType, int referenceAccountNumber, float balance) {
        this.id = id;
        this.accountType = accountType;
        this.referenceAccountNumber = referenceAccountNumber;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                ", referenceAccountNumber=" + referenceAccountNumber +
                ", balance=" + balance +
                '}';
    }
}

package com.szaruga.InternetBankingApplicationDemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name = "user_entity")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //userName ma nam sie laczyc z UserDetails
    private String userName;
    @Size(min = 9, max = 12)
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Account> account;

    public User() {
    }

    public User(long id, String userName, String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

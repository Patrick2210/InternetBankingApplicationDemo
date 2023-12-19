package com.szaruga.InternetBankingApplicationDemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name = "user_profile")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "email must be not empty")
    @Email
    private String email;
    @NotEmpty(message = "phone number must be not empty")
    @Size(min = 9, max = 12)
    private String phoneNumber;
    @NotEmpty(message = "password must be not empty")
    @Size(min = 6, message = "password must have at least 6 characters")
    private String password;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user",
//            cascade = CascadeType.ALL)
//    private UserDetails userDetails;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
//            cascade = CascadeType.ALL)
//    private List<Account> account;

    public User() {
    }

    public User(Long id, String email, String phoneNumber, String password) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<Account> getAccount() {
//        return account;
//    }
//
//    public void setAccount(List<Account> account) {
//        this.account = account;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

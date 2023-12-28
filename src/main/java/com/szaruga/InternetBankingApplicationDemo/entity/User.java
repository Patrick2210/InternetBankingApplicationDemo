package com.szaruga.InternetBankingApplicationDemo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "user_profile")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3, message = "firstname must contain at least 3 char.")
    private String firstName;
    @Size(min = 3, message = "lastname must contain at least 3 char.")
    private String lastName;
    @Past(message = "birthdate must be in the past")
    private LocalDate birthDate;
    @Size(min = 3, message = "number pesel must contain at least 3 char.")
    private String numberPesel;
    @Email
    private String email;
    @Size(min = 9, max = 12)
    private String phoneNumber;
    @Size(min = 6, message = "password must have at least 6 characters")
    private String password;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private UserDetails userDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Account> account;

    public User() {
    }

    public User(
            Long id,
            String firstName,
            String lastName,
            LocalDate birthDate,
            String numberPesel,
            String email,
            String phoneNumber,
            String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.numberPesel = numberPesel;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNumberPesel() {
        return numberPesel;
    }

    public void setNumberPesel(String numberPesel) {
        this.numberPesel = numberPesel;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", numberPesel='" + numberPesel + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

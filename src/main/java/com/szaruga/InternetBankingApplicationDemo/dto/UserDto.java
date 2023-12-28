package com.szaruga.InternetBankingApplicationDemo.dto;

import java.time.LocalDate;

public class UserDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
    private String numberPesel;
    private String password;
    private String passwordReset;
    private String email;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNumberPesel() {
        return numberPesel;
    }

    public void setNumberPesel(String numberPesel) {
        this.numberPesel = numberPesel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordReset() {
        return passwordReset;
    }

    public void setPasswordReset(String passwordReset) {
        this.passwordReset = passwordReset;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

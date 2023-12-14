package com.szaruga.InternetBankingApplicationDemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, message = "Fist Name must contain at least 3 char.")
    private String firstName;
    @Size(min = 3, message = "Last Name must contain at least 3 char.")
    private String lastName;
    private LocalDate birthDate;
    private String numberPesel;
    @Email
    private String email;

    public UserDetails() {
    }

    public UserDetails(int id, String firstName, String lastName, LocalDate birthDate, String numberPesel, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.numberPesel = numberPesel;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", numberPesel='" + numberPesel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

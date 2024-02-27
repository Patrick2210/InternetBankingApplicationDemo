package com.szaruga.InternetBankingApplicationDemo.dto.user;

import java.time.LocalDate;
/**
 * Data Transfer Object (DTO) for representing a user.
 */
public class UserDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String phoneNumber;
    private String numberPesel;
    private String password;
    private String repeatPassword;
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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Static method to obtain builder instance
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private UserDto userDto;

        public Builder() {
            userDto = new UserDto();
        }

        public Builder firstName(String firstName) {
            userDto.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            userDto.lastName = lastName;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            userDto.birthDate = birthDate;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            userDto.phoneNumber = phoneNumber;
            return this;
        }

        public Builder numberPesel(String numberPesel) {
            userDto.numberPesel = numberPesel;
            return this;
        }

        public Builder password(String password) {
            userDto.password = password;
            return this;
        }

        public Builder repeatPassword(String repeatPassword) {
            userDto.repeatPassword = repeatPassword;
            return this;
        }

        public Builder email(String email) {
            userDto.email = email;
            return this;
        }

        public UserDto build() {
            return userDto;
        }
    }
}

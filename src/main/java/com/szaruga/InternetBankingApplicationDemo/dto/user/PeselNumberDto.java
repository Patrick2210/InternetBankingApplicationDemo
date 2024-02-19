package com.szaruga.InternetBankingApplicationDemo.dto.user;
/**
 * Data Transfer Object (DTO) for representing a PESEL number.
 */
public class PeselNumberDto {
    private String peselNumber;

    public String getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(String peselNumber) {
        this.peselNumber = peselNumber;
    }
}

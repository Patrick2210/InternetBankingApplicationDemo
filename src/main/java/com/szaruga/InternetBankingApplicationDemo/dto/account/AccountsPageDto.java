package com.szaruga.InternetBankingApplicationDemo.dto.account;
/**
 * Data Transfer Object (DTO) for representing a page of accounts.
 */
public class AccountsPageDto {
    private int id;
    private int referenceAccountNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReferenceAccountNumber() {
        return referenceAccountNumber;
    }

    public void setReferenceAccountNumber(int referenceAccountNumber) {
        this.referenceAccountNumber = referenceAccountNumber;
    }
}

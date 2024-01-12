package com.szaruga.InternetBankingApplicationDemo.dto.userdetails;

public class UserDetailsDto {
    private String address;
    private String addressHomeNumber;
    String addressFlatNumber;
    private String correspondenceAddress;
    private String correspondenceAddressHomeNumber;
    private String correspondenceAddressFlatNumber;
    private String postCode;
    private String city;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressHomeNumber() {
        return addressHomeNumber;
    }

    public void setAddressHomeNumber(String addressHomeNumber) {
        this.addressHomeNumber = addressHomeNumber;
    }

    public String getAddressFlatNumber() {
        return addressFlatNumber;
    }

    public void setAddressFlatNumber(String addressFlatNumber) {
        this.addressFlatNumber = addressFlatNumber;
    }

    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public String getCorrespondenceAddressHomeNumber() {
        return correspondenceAddressHomeNumber;
    }

    public void setCorrespondenceAddressHomeNumber(String correspondenceAddressHomeNumber) {
        this.correspondenceAddressHomeNumber = correspondenceAddressHomeNumber;
    }

    public String getCorrespondenceAddressFlatNumber() {
        return correspondenceAddressFlatNumber;
    }

    public void setCorrespondenceAddressFlatNumber(String correspondenceAddressFlatNumber) {
        this.correspondenceAddressFlatNumber = correspondenceAddressFlatNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

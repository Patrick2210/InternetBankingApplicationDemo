package com.szaruga.InternetBankingApplicationDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Entity class representing a user details account.
 */
@Entity(name = "user_details")
public class UserDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
    private String addressHomeNumber;
    private String addressFlatNumber;
    private String correspondenceAddress;
    private String correspondenceAddressHomeNumber;
    private String correspondenceAddressFlatNumber;
    private String postCode;
    private String city;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    public UserDetailsEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "UserDetailsEntity{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", addressHomeNumber='" + addressHomeNumber + '\'' +
                ", addressFlatNumber='" + addressFlatNumber + '\'' +
                ", correspondenceAddress='" + correspondenceAddress + '\'' +
                ", correspondenceAddressHomeNumber='" + correspondenceAddressHomeNumber + '\'' +
                ", correspondenceAddressFlatNumber='" + correspondenceAddressFlatNumber + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public static class Builder {
        private UserDetailsEntity userDetailsEntity;

        public Builder() {
            userDetailsEntity = new UserDetailsEntity();
        }
        public Builder id(Integer id) {
            userDetailsEntity.setId(id);
            return this;
        }
        public Builder address(String address) {
            userDetailsEntity.setAddress(address);
            return this;
        }

        public Builder addressHomeNumber(String addressHomeNumber) {
            userDetailsEntity.setAddressHomeNumber(addressHomeNumber);
            return this;
        }

        public Builder addressFlatNumber(String addressFlatNumber) {
            userDetailsEntity.setAddressFlatNumber(addressFlatNumber);
            return this;
        }

        public Builder correspondenceAddress(String correspondenceAddress) {
            userDetailsEntity.setCorrespondenceAddress(correspondenceAddress);
            return this;
        }

        public Builder correspondenceAddressHomeNumber(String correspondenceAddressHomeNumber) {
            userDetailsEntity.setCorrespondenceAddressHomeNumber(correspondenceAddressHomeNumber);
            return this;
        }

        public Builder correspondenceAddressFlatNumber(String correspondenceAddressFlatNumber) {
            userDetailsEntity.setCorrespondenceAddressFlatNumber(correspondenceAddressFlatNumber);
            return this;
        }

        public Builder postCode(String postCode) {
            userDetailsEntity.setPostCode(postCode);
            return this;
        }

        public Builder city(String city) {
            userDetailsEntity.setCity(city);
            return this;
        }

        public UserDetailsEntity build() {
            return userDetailsEntity;
        }
    }
}

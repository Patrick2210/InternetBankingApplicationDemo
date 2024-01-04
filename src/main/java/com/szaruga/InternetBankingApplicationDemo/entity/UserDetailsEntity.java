package com.szaruga.InternetBankingApplicationDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "user_details")
public class UserDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
    private String correspondenceAddress;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    public UserDetailsEntity() {
    }

    public UserDetailsEntity(String address, String correspondenceAddress) {
        this.address = address;
        this.correspondenceAddress = correspondenceAddress;
    }

    public UserDetailsEntity(int id, String correspondenceAddress) {
        this.id = id;
        this.correspondenceAddress = correspondenceAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    @Override
    public String toString() {
        return "UserDetailsEntity{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", correspondenceAddress='" + correspondenceAddress + '\'' +
                '}';
    }
}

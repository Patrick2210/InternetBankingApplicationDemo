package com.szaruga.InternetBankingApplicationDemo.bulider_entity;

import com.szaruga.InternetBankingApplicationDemo.entity.UserDetailsEntity;

public class UserDetailsBuilder {
    public static UserDetailsEntity createTestUserDetailsOne() {
        return new UserDetailsEntity.Builder()
                .id(1)
                .address("Labedzia")
                .addressHomeNumber("11")
                .addressFlatNumber("1")
                .correspondenceAddress("Labedzia")
                .correspondenceAddressHomeNumber("11")
                .correspondenceAddressFlatNumber("1")
                .postCode("20-335")
                .city("Lublin")
                .build();
    }    public static UserDetailsEntity createTestUserDetailsTwo() {
        return new UserDetailsEntity.Builder()
                .id(1)
                .address("Labedzia")
                .addressHomeNumber("12")
                .addressFlatNumber("2")
                .correspondenceAddress("Labedzia")
                .correspondenceAddressHomeNumber("12")
                .correspondenceAddressFlatNumber("2")
                .postCode("20-335")
                .city("Lublin")
                .build();
    }
}

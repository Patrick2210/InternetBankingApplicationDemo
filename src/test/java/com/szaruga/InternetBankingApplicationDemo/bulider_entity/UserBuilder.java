package com.szaruga.InternetBankingApplicationDemo.bulider_entity;

import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;

import java.time.LocalDate;

public class UserBuilder {

    public static UserEntity createTestUserOne() {
        return new UserEntity.Builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .birthDate(LocalDate.now().minusYears(25))
                .numberPesel("1234567890")
                .email("john.doe@example.com")
                .phoneNumber("123-456-7890")
                .password("password123")
                .build();
    }

    public static UserEntity createTestUserTwo() {
        return new UserEntity.Builder()
                .firstName("Patryk")
                .lastName("Smith")
                .birthDate(LocalDate.now().minusYears(25))
                .numberPesel("0987654321")
                .email("smith@example.com")
                .phoneNumber("7890-456-123")
                .password("password321")
                .build();
    }
}
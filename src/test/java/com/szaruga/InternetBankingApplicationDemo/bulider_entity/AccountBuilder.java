package com.szaruga.InternetBankingApplicationDemo.bulider_entity;

import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;

import java.math.BigDecimal;

public class AccountBuilder {
    public static AccountEntity createTestAccountOne(UserEntity userEntity) {
        return new AccountEntity.Builder()
                .accountType("Savings")
                .balance(BigDecimal.valueOf(100))
                .referenceAccountNumber(123456)
                .user(userEntity)
                .build();
    }    public static AccountEntity createTestAccountTwo(UserEntity userEntity) {
        return new AccountEntity.Builder()
                .accountType("Main")
                .balance(BigDecimal.valueOf(1200))
                .referenceAccountNumber(654321)
                .user(userEntity)
                .build();
    }
}

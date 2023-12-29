package com.szaruga.InternetBankingApplicationDemo.mapper;

import com.szaruga.InternetBankingApplicationDemo.dto.UserDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;

public class UserMapper {

    public static UserEntity toEntity(UserDto dto) {
        return new UserEntity(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getBirthDate(),
                dto.getNumberPesel(),
                dto.getEmail(),
                dto.getPhoneNumber(),
                dto.getPassword());

    }
}

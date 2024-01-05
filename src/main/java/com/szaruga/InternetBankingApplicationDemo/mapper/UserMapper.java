package com.szaruga.InternetBankingApplicationDemo.mapper;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import com.szaruga.InternetBankingApplicationDemo.dto.user.UserPasswordUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.dto.user.UserUpdateDto;
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

    public static UserEntity updateEntity(UserUpdateDto updateDto) {
        return new UserEntity(
                updateDto.getFirstName(),
                updateDto.getLastName(),
                updateDto.getPhoneNumber(),
                updateDto.getPhoneNumber());
    }

    public static UserEntity updatePassword(UserPasswordUpdateDto passwordUpdateDto) {
        return new UserEntity(passwordUpdateDto.getPassword());
    }
}
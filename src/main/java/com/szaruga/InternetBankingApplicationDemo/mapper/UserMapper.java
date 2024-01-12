package com.szaruga.InternetBankingApplicationDemo.mapper;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import com.szaruga.InternetBankingApplicationDemo.dto.user.GetUserByIdDto;
import com.szaruga.InternetBankingApplicationDemo.dto.user.UsersPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserEntity toEntity(UserDto dto) {
        UserEntity user = new UserEntity();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setBirthDate(dto.getBirthDate());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }

    public static UsersPageDto mapUsersEntityToPaginationDto(UserEntity user) {
        UsersPageDto usersPageDto = new UsersPageDto();
        usersPageDto.setId(user.getId());
        usersPageDto.setFirstName(user.getFirstName());
        usersPageDto.setLastName(user.getLastName());
        usersPageDto.setBirthDate(user.getBirthDate());
        return usersPageDto;
    }

    public static List<UsersPageDto> mapUsersEntitiesToPageDtoList(List<UserEntity> content) {
        return content.stream()
                .map(UserMapper::mapUsersEntityToPaginationDto)
                .collect(Collectors.toList());
    }

    public static GetUserByIdDto mapUserEntityToGetUserByIdDto(UserEntity user) {
        GetUserByIdDto userPageDto = new GetUserByIdDto();
        userPageDto.setFirstName(user.getFirstName());
        userPageDto.setLastName(user.getLastName());
        userPageDto.setNumberPesel(user.getNumberPesel());
        userPageDto.setPhoneNumber(user.getPhoneNumber());
        userPageDto.setEmail(user.getEmail());
        return userPageDto;
    }
}
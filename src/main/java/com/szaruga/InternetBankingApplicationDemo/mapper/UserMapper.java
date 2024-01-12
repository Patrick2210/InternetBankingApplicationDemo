package com.szaruga.InternetBankingApplicationDemo.mapper;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import com.szaruga.InternetBankingApplicationDemo.dto.user.UserPageDto;
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

    public static UserPageDto mapUserEntityToPaginationDto(UserEntity user) {
        UserPageDto userRetrieveDto = new UserPageDto();
        userRetrieveDto.setId(user.getId());
        userRetrieveDto.setFirstName(user.getFirstName());
        userRetrieveDto.setLastName(user.getLastName());
        userRetrieveDto.setEmail(user.getEmail());
        userRetrieveDto.setPhoneNumber(user.getPhoneNumber());
        return userRetrieveDto;
    }

    public static List<UserPageDto> mapUserEntitiesToPaginationDtoList(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(UserMapper::mapUserEntityToPaginationDto)
                .collect(Collectors.toList());
    }
}
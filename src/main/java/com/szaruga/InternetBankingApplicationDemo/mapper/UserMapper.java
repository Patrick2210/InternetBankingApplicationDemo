package com.szaruga.InternetBankingApplicationDemo.mapper;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import com.szaruga.InternetBankingApplicationDemo.dto.user.GetUserByIdDto;
import com.szaruga.InternetBankingApplicationDemo.dto.user.UsersPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;

import java.util.Objects;

/**
 * Mapper class responsible for mapping between User DTOs and entities.
 */
public class UserMapper {
    /**
     * Converts a UserDto object to a UserEntity object.
     *
     * @param dto The UserDto object to convert.
     * @return The converted UserEntity object.
     */
    public static UserEntity toEntity(UserDto dto) {
        UserEntity user = new UserEntity();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setNumberPesel(dto.getNumberPesel());
        user.setBirthDate(dto.getBirthDate());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }

    /**
     * Maps a UserEntity object to a UsersPageDto object.
     *
     * @param userEntity The UserEntity object to map.
     * @return The mapped UsersPageDto object.
     */
    public static UsersPageDto mapUsersEntityToUsersPageDto(UserEntity userEntity) {
        UsersPageDto usersPageDto = new UsersPageDto();
        usersPageDto.setId(userEntity.getId());
        usersPageDto.setFirstName(userEntity.getFirstName());
        usersPageDto.setLastName(userEntity.getLastName());
        usersPageDto.setBirthDate(userEntity.getBirthDate());
        return usersPageDto;
    }

    /**
     * Maps a UserEntity object to a GetUserByIdDto object.
     *
     * @param user The UserEntity object to map.
     * @return The mapped GetUserByIdDto object.
     */
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
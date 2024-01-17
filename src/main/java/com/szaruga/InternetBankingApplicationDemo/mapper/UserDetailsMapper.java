package com.szaruga.InternetBankingApplicationDemo.mapper;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.GetUserDetailsByIdDto;
import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UserDetailsDto;
import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UsersDetailsPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserDetailsEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsMapper {
    public static UserDetailsEntity toEntity(UserDetailsDto dto) {
        UserDetailsEntity userDetails = new UserDetailsEntity();
        userDetails.setAddress(dto.getAddress());
        userDetails.setAddressHomeNumber(dto.getAddressHomeNumber());
        userDetails.setAddressFlatNumber(dto.getAddressFlatNumber());
        userDetails.setCorrespondenceAddressFlatNumber(dto.getCorrespondenceAddress());
        userDetails.setCorrespondenceAddressHomeNumber(dto.getCorrespondenceAddressHomeNumber());
        userDetails.setCorrespondenceAddressFlatNumber(dto.getCorrespondenceAddressFlatNumber());
        userDetails.setPostCode(dto.getPostCode());
        userDetails.setCity(dto.getCity());

        return userDetails;
    }

    public static UsersDetailsPageDto mapUsersDetailsEntityToPageDto(UserDetailsEntity userDetailsEntity) {
        UsersDetailsPageDto usersDetailsPageDto = new UsersDetailsPageDto();
        usersDetailsPageDto.setId(userDetailsEntity.getId());
        usersDetailsPageDto.setCity(userDetailsEntity.getCity());
        usersDetailsPageDto.setPostCode(userDetailsEntity.getPostCode());
        return usersDetailsPageDto;
    }

    public static GetUserDetailsByIdDto mapUserEntityToGetUserById(UserDetailsEntity userDetailsEntity) {
        GetUserDetailsByIdDto userDetailsByIdDto = new GetUserDetailsByIdDto();
        userDetailsByIdDto.setAddress(userDetailsEntity.getAddress());
        userDetailsByIdDto.setAddressHomeNumber(userDetailsEntity.getAddressHomeNumber());
        userDetailsByIdDto.setAddressFlatNumber(userDetailsEntity.getAddressFlatNumber());
        userDetailsByIdDto.setPostCode(userDetailsEntity.getPostCode());
        userDetailsByIdDto.setCity(userDetailsEntity.getCity());
        return userDetailsByIdDto;
    }
}
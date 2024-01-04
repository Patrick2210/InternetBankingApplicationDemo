package com.szaruga.InternetBankingApplicationDemo.mapper;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UserDetailsDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserDetailsEntity;

public class UserDetailsMapper {
    public static UserDetailsEntity toEntity(UserDetailsDto dto) {
        return new UserDetailsEntity(
                dto.getAddress(),
                dto.getCorrespondenceAddress());
    }
}

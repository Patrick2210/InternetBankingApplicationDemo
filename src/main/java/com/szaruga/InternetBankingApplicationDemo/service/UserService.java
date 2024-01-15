package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.user.*;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserHasAccountsException;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.mapper.UserMapper;
import com.szaruga.InternetBankingApplicationDemo.model.user.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.util.PageableUtils;
import com.szaruga.InternetBankingApplicationDemo.verification.userdto.VerificationUserDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userpasswordupdatedto.VerificationUserPasswordUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto.VerificationUserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final VerificationUserDto verificationUserDto;
    private final VerificationUserUpdateDto verificationUserUpdateDto;

    @Autowired
    public UserService(UserRepository userRepository,
                       VerificationUserDto verificationUserDto,
                       VerificationUserUpdateDto verificationUserUpdateDto) {
        this.userRepository = userRepository;
        this.verificationUserDto = verificationUserDto;
        this.verificationUserUpdateDto = verificationUserUpdateDto;
    }

    public Page<UsersPageDto> getUsersPagination(int pageNumber, int pageSize, String sort) {
        //todo zawezic sortowanie do: id / firstName/lastName/data uro
        Pageable pageable = PageableUtils.buildPageable(pageNumber, pageSize, sort);
        Page<UserEntity> userPage = userRepository.findAll(pageable);
        List<UsersPageDto> userPageDtoList = UserMapper.mapUsersEntitiesToPageDtoList(userPage.getContent());
        return new PageImpl<>(userPageDtoList, pageable, userPage.getTotalElements());
    }

    public GetUserByIdDto getUserById(long id) {
        final UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));
        return UserMapper.mapUserEntityToGetUserByIdDto(userEntity);
    }

    public void deleteUser(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));
        if (userEntity.getAccounts().isEmpty()) {
            userRepository.delete(userEntity);
        } else {
            throw new UserHasAccountsException(USER_HAS_ACCOUNT.getMessage());
        }

    }

    public CreateUser saveUser(UserDto dto) {
        verificationUserDto.userDto(dto);
        UserEntity save = userRepository.save(UserMapper.toEntity(dto));
        return new CreateUser(save.getId());
    }

    public void updateUser(long id, UserUpdateDto updateDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));

        verificationUserUpdateDto.userUpdateDto(updateDto);
        userEntity.setFirstName(updateDto.getFirstName());
        userEntity.setLastName(updateDto.getLastName());
        userEntity.setPhoneNumber(updateDto.getPhoneNumber());
        userEntity.setEmail(updateDto.getEmail());
        userRepository.save(userEntity);
    }

    public void updateUserPassword(long id, UserPasswordUpdateDto updatePasswordDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));

        VerificationUserPasswordUpdateDto.userPasswordUpdateDto(updatePasswordDto);
        userEntity.setPassword(updatePasswordDto.getPassword());
        userRepository.save(userEntity);
    }
}
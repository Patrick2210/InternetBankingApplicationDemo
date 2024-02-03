package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.user.*;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserHasAccountsException;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.IllegalSortingRequest;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.mapper.UserMapper;
import com.szaruga.InternetBankingApplicationDemo.model.user.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.util.SortingStringValues;
import com.szaruga.InternetBankingApplicationDemo.verification.userdto.VerificationUserDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userpasswordupdatedto.VerificationUserPasswordUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto.VerificationUserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

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

    public Page<UsersPageDto> getAllUsers(int pageNumber, int pageSize, String sortByInput) {
        Pageable pageable;
        if (sortByInput == null) {
            pageable = PageRequest.of(pageNumber, pageSize);
            return userRepository.findAll(pageable).map(UserMapper::mapUsersEntityToUsersPageDto);
        } else {
            Sort sort = Sort.by(Sort.Direction.ASC, sortByInput);
            pageable = PageRequest.of(pageNumber, pageSize, sort);
            for (String sortMessage : SortingStringValues.sortingMessages) {
                {
                    if (sortMessage.equalsIgnoreCase(sortByInput)) {
                        return userRepository.findAll(pageable).map(UserMapper::mapUsersEntityToUsersPageDto);
                    }
                }
            }
        }
        throw new IllegalSortingRequest(INVALID_SORT_FIELD.getMessage() + sortByInput);
    }

    public GetUserByIdDto getUserById(long id) {
        UserEntity userEntity = userRepository.findById(id)
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
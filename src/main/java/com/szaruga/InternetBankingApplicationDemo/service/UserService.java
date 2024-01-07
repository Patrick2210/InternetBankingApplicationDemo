package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.user.*;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.mapper.UserMapper;
import com.szaruga.InternetBankingApplicationDemo.model.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.verification.userdto.VerificationUserDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userpasswordupdatedto.VerificationUserPasswordUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto.VerificationUserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.USER_NOT_FOUND_WITH_ID;


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

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
        // jako return ma pojsc page z ogkreslona ilosc user na stronie
    }

    public UserEntity findUserById(long id) {
//        final UserEntity userEntity = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));

        //tutaj mapujesz na jakis obiekt

//        return // jakis obiekt dto
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));
    }

    public void deleteUser(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));
        userRepository.delete(userEntity);
        //todo sprawdzic czy user ma jakies konta jezeli sa nie widoczne to git delete
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
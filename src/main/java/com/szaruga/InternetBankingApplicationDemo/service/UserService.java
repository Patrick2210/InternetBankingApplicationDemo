package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import com.szaruga.InternetBankingApplicationDemo.dto.user.UserUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.mapper.UserMapper;
import com.szaruga.InternetBankingApplicationDemo.model.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.validation.user_dto.ValidationUserDto;
import com.szaruga.InternetBankingApplicationDemo.validation.user_upgrade_dto.ValidateUserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.USER_NOT_FOUND_WITH_ID;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final ValidationUserDto validationUserDto;
    private final ValidateUserUpdateDto validateUserUpdateDto;

    @Autowired
    public UserService(UserRepository userRepository, ValidationUserDto validationUserDto, ValidateUserUpdateDto validateUserUpdateDto) {
        this.userRepository = userRepository;
        this.validationUserDto = validationUserDto;
        this.validateUserUpdateDto = validateUserUpdateDto;
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));
    }

    public void deleteUser(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));
        userRepository.delete(userEntity);
    }

    public CreateUser saveUser(UserDto dto) {
        validationUserDto.validateDto(dto);
        UserEntity save = userRepository.save(UserMapper.toEntity(dto));
        return new CreateUser(save.getId());
    }

    public void updateUser(long id, UserUpdateDto updateDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));

        validateUserUpdateDto.validateUserUpdateDto(updateDto);
        userEntity.setFirstName(updateDto.getFirstName());
        userEntity.setLastName(updateDto.getLastName());
        userEntity.setPhoneNumber(updateDto.getPhoneNumber());
        userEntity.setEmail(updateDto.getEmail());
        userRepository.save(UserMapper.updateEntity(updateDto));
    }
}
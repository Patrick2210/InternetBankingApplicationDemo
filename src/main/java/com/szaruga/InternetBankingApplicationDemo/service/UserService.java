package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.UserDto;
import com.szaruga.InternetBankingApplicationDemo.dto.UserDtoUpdate;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.mapper.UserMapper;
import com.szaruga.InternetBankingApplicationDemo.model.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.validation.userdto.ValidationUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.USER_NOT_FOUND_WITH_ID;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final ValidationUserDto validationUserDto;

    @Autowired
    public UserService(UserRepository userRepository, ValidationUserDto validationUserDto) {
        this.userRepository = userRepository;
        this.validationUserDto = validationUserDto;
    }

    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity findUserById(long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        Predicate<? super UserEntity> predicate = user -> user.getId().equals(id);
        return optionalUser.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
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

    public void updateUser(UserDtoUpdate update) {
    }

}
package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.user.*;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import org.springframework.web.reactive.function.BodyInserters;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserHasAccountsException;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.IllegalSortingRequest;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.mapper.UserMapper;
import com.szaruga.InternetBankingApplicationDemo.model.user.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.util.SortingStringValues;
import com.szaruga.InternetBankingApplicationDemo.verification.userdto.VerificationUserDto;
import com.szaruga.InternetBankingApplicationDemo.verification.useremailupdatedto.VerificationEmailUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userpasswordupdatedto.VerificationUserPasswordUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto.VerificationUserUpdateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final VerificationUserDto verificationUserDto;
    private final VerificationUserUpdateDto verificationUserUpdateDto;
    private final VerificationUserPasswordUpdateDto verificationUserPasswordUpdateDto;
    private final VerificationEmailUpdateDto verificationEmailUpdateDto;
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository,
                       VerificationUserDto verificationUserDto,
                       VerificationUserUpdateDto verificationUserUpdateDto, VerificationUserPasswordUpdateDto verificationUserPasswordUpdateDto, VerificationEmailUpdateDto verificationEmailUpdateDto) {
        this.userRepository = userRepository;
        this.verificationUserDto = verificationUserDto;
        this.verificationUserUpdateDto = verificationUserUpdateDto;
        this.verificationUserPasswordUpdateDto = verificationUserPasswordUpdateDto;
        this.verificationEmailUpdateDto = verificationEmailUpdateDto;
    }

    @Autowired
    private WebClient.Builder webClientBuilder;


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

        verificationUserPasswordUpdateDto.userPasswordUpdateDto(updatePasswordDto);
        userEntity.setPassword(updatePasswordDto.getPassword());
        userRepository.save(userEntity);
    }

    public void updateUserEmail(long id, UserEmailUpdateDto emailUpdateDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id));

        verificationEmailUpdateDto.verificationEmailUpdateDto(emailUpdateDto);
        userEntity.setEmail(emailUpdateDto.getEmail());
        userRepository.save(userEntity);
    }

    public void sendRequest(String peselNumber) {
        String baseUrl = "http://localhost:8082/api/verify-pesel";
        webClientBuilder.build()
                .post()
                .uri(baseUrl)
                .body(BodyInserters.fromValue(peselNumber))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> System.out.println("Response from 2nd app: " + response),
                        error -> System.err.println("Error occurred: " + error.getMessage()));
    }

    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }
}
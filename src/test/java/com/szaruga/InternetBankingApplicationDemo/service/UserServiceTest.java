package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.bulider_entity.UserBuilder;
import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.user.PeselValidationException;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserHasAccountsException;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.model.user.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.verification.userdto.VerificationUserDto;
import com.szaruga.InternetBankingApplicationDemo.verification.useremailupdatedto.VerificationEmailUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userpasswordupdatedto.VerificationUserPasswordUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto.VerificationUserUpdateDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.szaruga.InternetBankingApplicationDemo.bulider_entity.AccountBuilder.createTestAccountOne;
import static com.szaruga.InternetBankingApplicationDemo.bulider_entity.AccountBuilder.createTestAccountTwo;
import static com.szaruga.InternetBankingApplicationDemo.bulider_entity.UserBuilder.createTestUserOne;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private WebClient webClient;
    @Mock
    private VerificationUserDto verificationUserDto;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(
                userRepository,
                verificationUserDto,
                new VerificationUserUpdateDto(),
                new VerificationUserPasswordUpdateDto(),
                new VerificationEmailUpdateDto(), webClient);
        userService = Mockito.spy(userService);
    }

    @Test
    public void testSaveNewUser_PeselValidationFailSuccess() {
        UserDto testUserDto = new UserDto.Builder()
                .build();
        doReturn(ResponseEntity.ok("OK")).when(userService).sendPeselValidationRequestToExternalApp(any());
        when(userRepository.save(any())).thenReturn(createTestUserOne());
        CreateUser createUser = userService.saveUser(testUserDto);
        assertEquals(1, createUser.getClientId());
    }

    @Test
    public void testSaveNewUser_PeselValidationFail() {
        UserDto testUserDto = new UserDto.Builder()
                .build();
        doReturn(ResponseEntity.badRequest().build()).when(userService).sendPeselValidationRequestToExternalApp(any());
        assertThrows(PeselValidationException.class, () -> userService.saveUser(testUserDto));
    }

    @Test
    public void testDeleteUser_WhenAccountExist() {
        UserEntity testUser = createTestUserOne();
        AccountEntity testAccountOne = createTestAccountOne(testUser);
        AccountEntity testAccountTwo = createTestAccountTwo(testUser);
        doReturn(Optional.of(testUser)).when(userRepository).findById(1L);
        assertThrows(UserHasAccountsException.class, () -> userService.deleteUser(testUser.getId()));
    }


    @Test
    public void testDeleteUser_WhenUserHasNoAccounts() {
        UserEntity testUser = createTestUserOne();
        doReturn(Optional.of(testUser)).when(userRepository).findById(1L);
        //todo musze dodac do Listy<Accounts> aby nie byl null
        when(testUser.getAccounts().isEmpty()).thenReturn(true);
        userService.deleteUser(testUser.getId());
        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    public void testDeleteUser_WhenUserNotFound() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
        verify(userRepository, never()).delete(any());
    }
}

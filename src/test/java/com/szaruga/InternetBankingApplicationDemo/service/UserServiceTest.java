package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.bulider_entity.UserBuilder;
import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.user.PeselValidationException;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

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
        doReturn(ResponseEntity.ok("Gitowa")).when(userService).sendPeselValidationRequestToExternalApp(any());
        when(userRepository.save(any())).thenReturn(UserBuilder.createTestUserOne());
        CreateUser createUser = userService.saveUser(testUserDto);
        Assertions.assertEquals(1, createUser.getClientId());
    }

    @Test
    public void testSaveNewUser_PeselValidationFail() {
        UserDto testUserDto = new UserDto.Builder()
                .build();
        doReturn(ResponseEntity.badRequest().build()).when(userService).sendPeselValidationRequestToExternalApp(any());
        Assertions.assertThrows(PeselValidationException.class, () -> userService.saveUser(testUserDto));
    }
}

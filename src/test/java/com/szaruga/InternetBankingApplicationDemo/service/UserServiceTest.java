package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import com.szaruga.InternetBankingApplicationDemo.dto.user.UsersPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.user.PeselValidationException;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserHasAccountsException;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.IllegalSortingRequest;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.model.user.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.verification.userdto.VerificationUserDto;
import com.szaruga.InternetBankingApplicationDemo.verification.useremailupdatedto.VerificationEmailUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userpasswordupdatedto.VerificationUserPasswordUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.verification.userupgradedto.VerificationUserUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;

import static com.szaruga.InternetBankingApplicationDemo.bulider_entity.AccountBuilder.*;
import static com.szaruga.InternetBankingApplicationDemo.bulider_entity.UserBuilder.*;
import static com.szaruga.InternetBankingApplicationDemo.bulider_entity.UserBuilder.createTestUserOne;
import static com.szaruga.InternetBankingApplicationDemo.constant.TestApplicationConstants.INVALID_SORT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link UserService} to verify its behavior.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    // Mocks for dependencies
    @Mock
    private UserRepository userRepository;
    @Mock
    private WebClient webClient;
    @Mock
    private VerificationUserDto verificationUserDto;
    // Instance of UserService to be tested
    private UserService userService;

    /**
     * Set up method to initialize the UserService instance before each test.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the UserService with mocked dependencies
        userService = new UserService(
                userRepository,
                verificationUserDto,
                new VerificationUserUpdateDto(),
                new VerificationUserPasswordUpdateDto(),
                new VerificationEmailUpdateDto(), webClient);

        // Create a spy of the UserService instance for potential verification and stubbing
        userService = Mockito.spy(userService);
    }

    /**
     * Test case for {@link UserService#saveUser(UserDto)} to verify behavior when
     * attempting to save a new user with successful PESEL validation.
     */
    @Test
    public void testSaveNewUsers_PeselValidationFailSuccess() {
        // Create a test user DTO without providing PESEL
        UserDto testUserDto = new UserDto.Builder().build();

        // Mock the userService to return an OK response when sending a PESEL validation request
        doReturn(ResponseEntity.ok("OK")).when(userService).sendPeselValidationRequestToExternalApp(any());

        // Mock the userRepository to return a test user entity when saving the user
        when(userRepository.save(any())).thenReturn(createTestUserOne());

        // Invoke the saveUser method and verify the returned CreateUser object
        CreateUser createUser = userService.saveUser(testUserDto);

        // Assert that the client ID in the CreateUser object matches the expected value
        assertEquals(1, createUser.getClientId());
    }

    /**
     * Test case for {@link UserService#saveUser(UserDto)} to verify behavior when
     * attempting to save a new user with failed PESEL validation.
     */
    @Test
    public void testSaveNewUsers_PeselValidationFail() {
        // Create a test user DTO without providing PESEL
        UserDto testUserDto = new UserDto.Builder().build();

        // Mock the userService to return a bad request response when sending a PESEL validation request
        doReturn(ResponseEntity.badRequest().build()).when(userService).sendPeselValidationRequestToExternalApp(any());

        // Verify that a PeselValidationException is thrown when attempting to save the user
        assertThrows(PeselValidationException.class, () -> userService.saveUser(testUserDto));
    }

    /**
     * Test case for {@link UserService#deleteUser(long)} to verify behavior when
     * attempting to delete a user who has associated accounts.
     */
    @Test
    public void testDeleteUsers_WhenUserHasAccounts() {
        // Create a test user
        UserEntity testUser = createTestUserOne();

        // Create test accounts associated with the user
        AccountEntity testAccountOne = createTestAccountOne(testUser);
        AccountEntity testAccountTwo = createTestAccountTwo(testUser);

        // Add the test accounts to a list
        List<AccountEntity> accountEntities = new ArrayList<>();
        accountEntities.add(testAccountOne);
        accountEntities.add(testAccountTwo);

        // Set the list of accounts for the test user
        testUser.setAccounts(accountEntities);

        // Mock userRepository to return the test user when findById is called with userId
        doReturn(Optional.of(testUser)).when(userRepository).findById(1L);

        // Verify that an exception is thrown when attempting to delete the user
        assertThrows(UserHasAccountsException.class, () -> userService.deleteUser(testUser.getId()));
    }

    /**
     * Test case for {@link UserService#deleteUser(long)} to verify behavior when
     * attempting to delete a user who has no associated accounts.
     */
    @Test
    public void testDeleteUsers_WhenUserHasNoAccounts() {
        // Define test parameters
        long userId = 1L;

        // Create a test user with no associated accounts
        UserEntity testUser = createTestUserOne();
        testUser.setAccounts(new ArrayList<>());

        // Mock userRepository to return the test user when findById is called with userId
        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(testUser));

        // Call deleteUser method with userId
        userService.deleteUser(userId);

        // Verify that userRepository findById method is called with userId
        verify(userRepository).findById(userId);

        // Verify that userRepository delete method is called with the test user
        verify(userRepository).delete(testUser);
    }

    /**
     * Test case for {@link UserService#deleteUser(long)} to verify behavior when
     * attempting to delete a user that is not found.
     */
    @Test
    public void testDeleteUsers_WhenUserNotFound() {
        // Define test parameters
        long userId = 1L;

        // Mock userRepository to return empty Optional when findById is called with userId
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Verify that deleting a user that is not found throws UserNotFoundException
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));

        // Verify that userRepository delete method is never called
        verify(userRepository, never()).delete(any());
    }

    /**
     * Test case for {@link UserService#getAllUsers(int, int, String)} to verify the retrieval of all users
     * when sorting input is null.
     */
    @Test
    public void testGetAllUsers_WhenSortByInputIsNull() {
        // Define test parameters
        int pageNumber = 0;
        int pageSize = 10;

        // Create pageable object without sorting
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Create a list and add test users
        List<UserEntity> usersList = new ArrayList<>();
        usersList.add(createTestUserOne());
        usersList.add(createTestUserTwo());

        // Create a page of users
        Page<UserEntity> usersPage = new PageImpl<>(usersList, pageable, usersList.size());

        // Mock the repository method call
        when(userRepository.findAll(pageable)).thenReturn(usersPage);

        // Call the service method with null sorting input
        Page<UsersPageDto> resultPage = userService.getAllUsers(pageNumber, pageSize, null);

        // Verify that the repository method was called with the correct pageable object
        verify(userRepository).findAll(pageable);

        // Assertions
        // Verify that the result page is not null
        assertNotNull(resultPage);
    }

    /**
     * Test case for {@link UserService#getAllUsers(int, int, String)} to verify the retrieval of all users
     * when sorting by a valid input.
     */
    @Test
    public void testGetAllUsers_WhenSortByInputIsValid() {
        // Define test parameters
        int pageNumber = 0;
        int pageSize = 10;

        // Possible values include: id, referenceAccountNumber, postcode, city, firstName, lastName, birthday
        String sortByInput = "id";

        // Create sort object based on input
        Sort sort = Sort.by(Sort.Direction.ASC, sortByInput);

        // Create pageable object
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        // Create a list and add test users
        List<UserEntity> usersList = new ArrayList<>();
        usersList.add(createTestUserOne());
        usersList.add(createTestUserTwo());

        // Create a page of users
        Page<UserEntity> usersPage = new PageImpl<>(usersList, pageable, usersList.size());

        // Mock the repository method call
        when(userRepository.findAll(pageable)).thenReturn(usersPage);

        // Call the service method
        Page<UsersPageDto> resultPage = userService.getAllUsers(pageNumber, pageSize, sortByInput);

        // Verify that the repository method was called with the correct pageable object
        verify(userRepository).findAll(pageable);

        // Assertions
        // Verify that the result page is not null
        assertNotNull(resultPage);
    }

    /**
     * Test case for {@link UserService#getAllUsers(int, int, String)} to verify behavior when
     * sorting input is invalid.
     */
    @Test
    public void testGetAllUsers_WhenSortByInputIsInvalid() {
        // Define test parameters
        int pageNumber = 0;
        int pageSize = 10;

        // Verify that calling the service method with invalid sorting input throws IllegalSortingRequest
        assertThrows(IllegalSortingRequest.class, () -> userService.getAllUsers(pageNumber, pageSize, INVALID_SORT.getMessage()));
    }
}

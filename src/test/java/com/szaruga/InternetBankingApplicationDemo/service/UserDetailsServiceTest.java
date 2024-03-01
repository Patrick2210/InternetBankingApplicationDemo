package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UsersDetailsPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserDetailsEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.IllegalSortingRequest;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserDetailsRepository;
import com.szaruga.InternetBankingApplicationDemo.verification.userdetailsdto.ValidationUserDetailsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.*;

import static com.szaruga.InternetBankingApplicationDemo.bulider_entity.UserDetailsBuilder.*;
import static com.szaruga.InternetBankingApplicationDemo.constant.TestApplicationConstants.INVALID_SORT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * Test class for {@link UserDetailsService} to verify its behavior.
 */
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceTest {
    // Mocks for dependencies
    @Mock
    private UserDetailsRepository userDetailsRepository;

    // Instance of UserDetailsService to be tested
    private UserDetailsService userDetailsService;

    /**
     * Set up method to initialize the UserDetailsService instance before each test.
     */
    @BeforeEach
    public void setUp() {
        userDetailsService = new UserDetailsService(
                userDetailsRepository,
                new ValidationUserDetailsDto());
    }

    /**
     * Test case for {@link UserDetailsService#getAllUsersDetails(int, int, String)} to verify the retrieval of all
     * users details when sorting input is null.
     */
    @Test
    public void testGetAllUsersDetails_WhenSortByInputIsNull() {
        // Define test parameters
        int pageNumber = 0;
        int pageSize = 10;

        // Create pageable object without sorting
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Create a list and add test users details
        List<UserDetailsEntity> usersDetailsList = new ArrayList<>();
        usersDetailsList.add(createTestUserDetailsOne());
        usersDetailsList.add(createTestUserDetailsTwo());

        // Create a page of users details
        Page<UserDetailsEntity> usersPage = new PageImpl<>(usersDetailsList, pageable, usersDetailsList.size());

        // Mock the repository method call
        when(userDetailsRepository.findAll(pageable)).thenReturn(usersPage);

        // Call the service method with null sorting input
        Page<UsersDetailsPageDto> resultPage = userDetailsService.getAllUsersDetails(pageNumber, pageSize, null);

        // Verify that the repository method was called with the correct pageable object
        verify(userDetailsRepository).findAll(pageable);

        // Assertions
        // Verify that the result page is not null
        assertNotNull(resultPage);
    }

    /**
     * Test case for {@link UserDetailsService#getAllUsersDetails(int, int, String)} to verify the retrieval of all
     * users details when sorting by a valid input.
     */
    @Test
    public void testGetAllUsersDetails_WhenSortByInputIsValid() {
        // Define test parameters
        int pageNumber = 0;
        int pageSize = 10;

        // Possible values include: id, referenceAccountNumber, postcode, city, firstName, lastName, birthday
        String sortByInput = "id";

        // Create sort object based on input
        Sort sort = Sort.by(Sort.Direction.ASC, sortByInput);

        // Create pageable object
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        // Create a list and add test users details
        List<UserDetailsEntity> usersDetailsList = new ArrayList<>();
        usersDetailsList.add(createTestUserDetailsOne());
        usersDetailsList.add(createTestUserDetailsTwo());

        // Create a page of users details
        Page<UserDetailsEntity> usersPage = new PageImpl<>(usersDetailsList, pageable, usersDetailsList.size());

        // Mock the repository method call
        when(userDetailsRepository.findAll(pageable)).thenReturn(usersPage);

        // Call the service method
        Page<UsersDetailsPageDto> resultPage = userDetailsService.getAllUsersDetails(pageNumber, pageSize, sortByInput);

        // Verify that the repository method was called with the correct pageable object
        verify(userDetailsRepository).findAll(pageable);

        // Assertions
        // Verify that the result page is not null
        assertNotNull(resultPage);
    }

    /**
     * Test case for {@link UserDetailsService#getAllUsersDetails(int, int, String)} to verify behavior when
     * sorting input is invalid.
     */
    @Test
    public void testGetAllUsersDetails_WhenSortByInputIsInvalid() {
        // Define test parameters
        int pageNumber = 0;
        int pageSize = 10;

        // Verify that calling the service method with invalid sorting input throws IllegalSortingRequest
        assertThrows(IllegalSortingRequest.class, () -> userDetailsService.getAllUsersDetails(pageNumber, pageSize, INVALID_SORT.getMessage()));
    }
}

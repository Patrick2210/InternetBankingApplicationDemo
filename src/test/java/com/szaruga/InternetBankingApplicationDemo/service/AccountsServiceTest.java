package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.account.AccountsPageDto;
import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UsersDetailsPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;
import com.szaruga.InternetBankingApplicationDemo.entity.UserDetailsEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.validation.IllegalSortingRequest;
import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.util.AccountUtils;
import com.szaruga.InternetBankingApplicationDemo.verification.accountdto.ValidationAccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.*;

import static com.szaruga.InternetBankingApplicationDemo.bulider_entity.AccountBuilder.*;
import static com.szaruga.InternetBankingApplicationDemo.bulider_entity.UserBuilder.createTestUserOne;
import static com.szaruga.InternetBankingApplicationDemo.constant.TestApplicationConstants.INVALID_SORT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link AccountsService} to verify its behavior.
 */
@ExtendWith(MockitoExtension.class)
public class AccountsServiceTest {
    // Mocks for dependencies
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private UserRepository userRepository;
    // Instance of AccountsService to be tested
    private AccountsService accountsService;

    @BeforeEach
    public void setUp() {
        accountsService = new AccountsService(
                accountRepository,
                new AccountUtils(accountRepository),
                new ValidationAccountDto(),
                userRepository);
    }
    /**
     * Test case for {@link AccountsService#getAllAccounts(int, int, String)} to verify the retrieval of all
     * users details when sorting input is null.
     */
    @Test
    public void getAllAccounts_WhenSortByInputIsNull() {
        // Define test parameters
        int pageNumber = 0;
        int pageSize = 10;

        // Create pageable object without sorting
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Create a list and add test user accounts
        List<AccountEntity> accountsList = new ArrayList<>();
        accountsList.add(createTestAccountOne(createTestUserOne()));
        accountsList.add(createTestAccountTwo(createTestUserOne()));

        // Create a page of accounts
        Page<AccountEntity> accountsPage = new PageImpl<>(accountsList, pageable, accountsList.size());

        // Mock the repository method call
        when(accountRepository.findAll(pageable)).thenReturn(accountsPage);

        // Call the service method with null sorting input
        Page<AccountsPageDto> resultPage = accountsService.getAllAccounts(pageNumber, pageSize, null);

        // Verify that the repository method was called with the correct pageable object
        verify(accountRepository).findAll(pageable);

        // Assertions
        // Verify that the result page is not null
        assertNotNull(resultPage);
    }
    /**
     * Test case for {@link AccountsService#getAllAccounts(int, int, String)} to verify the retrieval of all
     * users details when sorting by a valid input.
     */
    @Test
    public void getAllAccounts_WhenSortByInputIsValid() {
        // Define test parameters
        int pageNumber = 0;
        int pageSize = 10;

        // Possible values include: id, referenceAccountNumber, postcode, city, firstName, lastName, birthday
        String sortByInput = "postcode";

        // Create sort object based on input
        Sort sort = Sort.by(Sort.Direction.ASC, sortByInput);

        // Create pageable object
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        // Create a list and add test user accounts
        List<AccountEntity> accountsList = new ArrayList<>();
        accountsList.add(createTestAccountOne(createTestUserOne()));
        accountsList.add(createTestAccountTwo(createTestUserOne()));

        // Create a page of accounts
        Page<AccountEntity> accountsPage = new PageImpl<>(accountsList, pageable, accountsList.size());

        // Mock the repository method call
        when(accountRepository.findAll(pageable)).thenReturn(accountsPage);

        // Call the service method
        Page<AccountsPageDto> resultPage = accountsService.getAllAccounts(pageNumber, pageSize, sortByInput);

        // Verify that the repository method was called with the correct pageable object
        verify(accountRepository).findAll(pageable);

        // Assertions
        // Verify that the result page is not null
        assertNotNull(resultPage);
    }
    /**
     * Test case for {@link AccountsService#getAllAccounts(int, int, String)} to verify behavior when
     * sorting input is invalid.
     */
    @Test
    public void getAllAccounts_WhenSortByInputIsInvalid() {
        // Define test parameters
        int pageNumber = 0;
        int pageSize = 10;

        // Verify that calling the service method with invalid sorting input throws IllegalSortingRequest
        assertThrows(IllegalSortingRequest.class, () -> accountsService.getAllAccounts(pageNumber, pageSize, INVALID_SORT.getMessage()));
    }
}
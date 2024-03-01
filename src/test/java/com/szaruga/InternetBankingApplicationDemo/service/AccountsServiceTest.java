package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.IllegalSortingRequest;
import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.util.AccountUtils;
import com.szaruga.InternetBankingApplicationDemo.verification.accountdto.ValidationAccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.szaruga.InternetBankingApplicationDemo.constant.TestApplicationConstants.INVALID_SORT;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link AccountsService} to verify its behavior.
 */
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

    @Test
    public void getAllAccounts_WhenSortByInputIsNull() {
    }

    @Test
    public void getAllAccounts_WhenSortByInputIsValid() {
    }

    @Test
    public void getAllAccounts_WhenSortByInputIsInvalid() {
        // Define test parameters
        int pageNumber = 0;
        int pageSize = 10;

        // Verify that calling the service method with invalid sorting input throws IllegalSortingRequest
        assertThrows(IllegalSortingRequest.class, () -> accountsService.getAllAccounts(pageNumber, pageSize, INVALID_SORT.getMessage()));
    }
}
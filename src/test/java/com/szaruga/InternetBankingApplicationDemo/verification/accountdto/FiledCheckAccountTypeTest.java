package com.szaruga.InternetBankingApplicationDemo.verification.accountdto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;
import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.szaruga.InternetBankingApplicationDemo.verification.accountdto.FiledCheckAccountType.isAccountTypeExists;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link FiledCheckAccountType} to verify its behavior.
 */
@ExtendWith(MockitoExtension.class)
public class FiledCheckAccountTypeTest {

    // Mock for the AccountRepository dependency
    @Mock
    private static AccountRepository accountRepository;

    // Instance of FiledCheckAccountType to be tested
    private FiledCheckAccountType filedCheckAccountType;

    /**
     * Set up method to initialize the FiledCheckAccountType instance before each test.
     */
    @BeforeEach
    public void setUp() {
        filedCheckAccountType = new FiledCheckAccountType(accountRepository);
    }

    /**
     * Test method to verify the behavior when the account type exists.
     */
    @Test
    public void testIsAccountTypeExists_WhenTypeExists() {
        // Define test account type
        String accountType = "Main";

        // Stub the behavior of accountRepository.existsByAccountType() method
        when(accountRepository.existsByAccountType(accountType)).thenReturn(true);

        // Assert that ValidationException is thrown when account type exists
        assertThrows(ValidationException.class, () -> isAccountTypeExists(accountType));

        // Verify that accountRepository.existsByAccountType() method is called
        verify(accountRepository).existsByAccountType(accountType);
    }
    /**
     * Test method to verify the behavior when the account type does not exist.
     */
    @Test
    public void isAccountTypeExists_WhenTypeDoesNotExist() {
        // Define test account type
        String accountType = "Savings";

        // Stub the behavior of accountRepository.existsByAccountType() method
        when(accountRepository.existsByAccountType(accountType)).thenReturn(false);

        // Assert that no exception is thrown when account type does not exist
        assertDoesNotThrow(() -> isAccountTypeExists(accountType));

        // Verify that accountRepository.existsByAccountType() method is called
        verify(accountRepository).existsByAccountType(accountType);
    }
}

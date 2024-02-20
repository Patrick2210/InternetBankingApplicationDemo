package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link AccountUtils}
 */
public class AccountUtilsTest {

    private AccountRepository accountRepository;
    private AccountUtils accountUtils;

    @Before("")
    public void setUp() {
        // Mocking the AccountRepository
        accountRepository = mock(AccountRepository.class);
        // Creating an instance of AccountUtils with the mocked repository
        accountUtils = new AccountUtils(accountRepository);
    }

    @Test
    public void testGenerateReferenceAccountNumber() {

    }

    @Test
    public void testGenerateReferenceAccountNumber1() {

    }
}

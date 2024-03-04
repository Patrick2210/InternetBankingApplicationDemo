package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link AccountUtils}
 */
@ExtendWith(MockitoExtension.class)
public class AccountUtilsTest {
    @Mock
    private AccountRepository accountRepository;
    private AccountUtils accountUtils;

    @BeforeEach()
    public void setUp() {
        // Creating an instance of AccountUtils with the mocked repository
        accountUtils = new AccountUtils(accountRepository);
    }
}

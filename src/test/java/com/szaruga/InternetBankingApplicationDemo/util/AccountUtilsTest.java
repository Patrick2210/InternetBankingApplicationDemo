package com.szaruga.InternetBankingApplicationDemo.util;

import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for {@link AccountUtils}
 */
@ExtendWith(MockitoExtension.class)
public class AccountUtilsTest {
    @Mock
    private AccountUtils accountUtils;
    @InjectMocks
    private  AccountRepository accountRepository;
}

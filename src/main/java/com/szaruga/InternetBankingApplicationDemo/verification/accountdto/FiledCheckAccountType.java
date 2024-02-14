package com.szaruga.InternetBankingApplicationDemo.verification.accountdto;

import com.szaruga.InternetBankingApplicationDemo.exception.validation.ValidationException;
import com.szaruga.InternetBankingApplicationDemo.jpa.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;
import static com.szaruga.InternetBankingApplicationDemo.util.ValidationDtoUtils.*;

/**
 * Utility class for verifying and validating account types.
 */
@Component
public class FiledCheckAccountType {
    private static AccountRepository repository;

    /**
     * Constructor for FiledCheckAccountType.
     *
     * @param repository The repository for accessing account-related data.
     */
    @Autowired
    public FiledCheckAccountType(AccountRepository repository) {
        FiledCheckAccountType.repository = repository;
    }

    /**
     * Validates the provided account type.
     *
     * @param accountType The account type to validate.
     * @throws ValidationException If the account type is not valid.
     */
    public static void validate(String accountType) {
        checkNotNull(accountType, ACCOUNT_TYPE.getMessage());
        checkNotEmpty(accountType, ACCOUNT_TYPE.getMessage());
        checkMinSize(accountType, ACCOUNT_TYPE.getMessage());
        checkMaxSize(accountType, ACCOUNT_TYPE.getMessage());
        checkSpecialCharacters(accountType, ACCOUNT_TYPE.getMessage());
        isAccountTypeExists(accountType);
    }

    /**
     * Checks if the provided account type already exists.
     *
     * @param accountType The account type to check.
     * @throws ValidationException If the account type already exists.
     */
    public static void isAccountTypeExists(String accountType) {
        boolean isExist = repository.existsByAccountType(accountType);
        if (isExist) {
            throw new ValidationException(ACCOUNT_TYPE.getMessage() + NAMING_ALREADY_EXIST.getMessage());
        }
    }
}
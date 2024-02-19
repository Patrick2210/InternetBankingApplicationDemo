package com.szaruga.InternetBankingApplicationDemo.jpa;

import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing AccountEntity objects.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    /**
     * Checks if an account exists by the reference account number.
     *
     * @param referenceAccountNumber The reference account number to check.
     * @return True if an account with the specified reference account number exists, false otherwise.
     */
    boolean existsByReferenceAccountNumber(int referenceAccountNumber);

    /**
     * Checks if an account exists by the account type.
     *
     * @param accountType The account type to check.
     * @return True if an account with the specified account type exists, false otherwise.
     */
    boolean existsByAccountType(String accountType);
}

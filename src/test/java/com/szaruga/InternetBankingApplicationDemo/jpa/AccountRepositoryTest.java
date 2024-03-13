package com.szaruga.InternetBankingApplicationDemo.jpa;

import com.szaruga.InternetBankingApplicationDemo.entity.AccountEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static com.szaruga.InternetBankingApplicationDemo.bulider_entity.AccountBuilder.createTestAccountOne;
import static com.szaruga.InternetBankingApplicationDemo.bulider_entity.AccountBuilder.createTestAccountTwo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Test case for {@link AccountRepository#save(Object)} to verify that accounts are saved successfully.
     * This test checks if the saved accounts are not null and have a valid ID (greater than 0).
     */
    @Test
    public void accountRepository_SaveAll_ReturnSavedAccount() {
        // Saving a test account into the repository
        AccountEntity savedTestAccount = accountRepository.save(createTestAccountOne(any()));

        // Retrieving the saved account by ID
        Optional<AccountEntity> accountReturn = accountRepository.findById(createTestAccountOne(any()).getId());

        // Assertions
        assertThat(accountReturn).isPresent();
        assertThat(accountReturn.get()).isEqualTo(savedTestAccount);
    }

    /**
     * Test case for {@link AccountRepository#findAll()} to verify that more than one accounts is returned.
     * This test also ensures that the number of accounts returned is as expected after saving data into the repository.
     * In the application, there are three places where data is saved into the userRepository.
     */
    @Test
    public void accountRepository_GetAll_ReturnMoreThenOneAccount() {
        // Saving a test accounts into the repository
        accountRepository.save(createTestAccountOne(any()));
        accountRepository.save(createTestAccountTwo(any()));

        // Retrieving all accounts from the repository
        List<AccountEntity> accountEntityList = accountRepository.findAll();

        // Assertions
        assertThat(accountEntityList).isNotNull();
        assertThat(accountEntityList.size()).isEqualTo(2);
    }

    /**
     * Test case for {@link AccountRepository#findById(Object)} to verify that ID retrieves the correct accounts.
     * This test checks if the repository returns the correct accounts by ID.
     */
    @Test
    public void accountRepository_FindById_ReturnAccount() {
        // Saving a test account into the repository
        AccountEntity savedTestAccount = accountRepository.save(createTestAccountOne(any()));

        // Retrieving the saved account by ID
        Optional<AccountEntity> accountReturn = accountRepository.findById(createTestAccountOne(any()).getId());

        // Assertions
        assertThat(accountReturn).isPresent();
        assertThat(accountReturn.get()).isEqualTo(savedTestAccount);
    }

    /**
     * Test case for {@link AccountRepository#delete(Object)} to verify that accounts are successfully deleted from the repository.
     * This test checks if the repository correctly deletes an accounts and returns an empty repository.
     */
    @Test
    public void accountRepository_Delete_ReturnEmptyRepository() {
        // Saving a test account into the repository
        accountRepository.save(createTestAccountOne(any()));

        // Retrieving the saved account by ID
        Optional<AccountEntity> accountReturn = accountRepository.findById(createTestAccountOne(any()).getId());
        assertThat(accountReturn).isPresent();

        // Deleting account
        accountRepository.delete(createTestAccountOne(any()));

        // Retrieving the account again after deletion
        Optional<AccountEntity> accountOptionalAfterDeletion = accountRepository.findById(createTestAccountOne(any()).getId());

        // Assertions
        assertThat(accountOptionalAfterDeletion).isEmpty();
    }
}

package com.szaruga.InternetBankingApplicationDemo.jpa;

import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link UserRepository} to verify its functionality.
 * This test class is annotated with {@link DataJpaTest} and {@link AutoConfigureTestDatabase}.
 */
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    //test users
    private final UserEntity testUserOne = new UserEntity.Builder()
            .firstName("John")
            .lastName("Doe")
            .birthDate(LocalDate.now().minusYears(25))
            .numberPesel("1234567890")
            .email("john.doe@example.com")
            .phoneNumber("123-456-7890")
            .password("password123")
            .build();

    private final UserEntity testUserTwo = new UserEntity.Builder()
            .firstName("Patryk")
            .lastName("Smith")
            .birthDate(LocalDate.now().minusYears(25))
            .numberPesel("0987654321")
            .email("smith@example.com")
            .phoneNumber("7890-456-123")
            .password("password321")
            .build();

    /**
     * Test case for {@link UserRepository#save(Object)} to verify that a user is saved successfully.
     * This test checks if the saved user is not null and has a valid ID (greater than 0).
     */
    @Test
    public void userRepository_SaveAll_ReturnSavedUser() {
        //Act
        UserEntity saveUser = userRepository.save(testUserOne);

        //Assert
        assertThat(saveUser).isNotNull();
        assertThat(saveUser.getId()).isGreaterThan(0);
    }

    /**
     * Test case for {@link UserRepository#findAll()} to verify that more than one user is returned.
     * This test also ensures that the number of users returned is as expected after saving data into the repository.
     * In the application, there are three places where data is saved into the userRepository.
     */
    @Test
    public void userRepository_GetAll_ReturnMoreThenOneUser() {
        // Saving two test users into the repository
        userRepository.save(testUserOne);
        userRepository.save(testUserTwo);

        // Retrieving all users from the repository
        List<UserEntity> usersList = userRepository.findAll();

        // Assertions
        assertThat(usersList).isNotNull();

        // There are three places in the application where data is saved into the userRepository,
        // hence the total number of users should be 2 (testUserOne and testUserTwo)
        assertThat(usersList.size()).isEqualTo(5);
    }

    /**
     * Test case for {@link UserRepository#findById(Object)} to verify that the correct user is retrieved by ID.
     * This test checks if the repository returns the correct user by ID.
     */
    @Test
    public void userRepository_FindById_ReturnMoreThenOneUser() {
        // Saving a test user into the repository
        UserEntity savedUser = userRepository.save(testUserOne);

        // Retrieving the saved user by ID
        Optional<UserEntity> userReturn = userRepository.findById(testUserOne.getId());

        // Assertions
        assertThat(userReturn).isPresent();
        assertThat(userReturn.get()).isEqualTo(savedUser);
    }
}

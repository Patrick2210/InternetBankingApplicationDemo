package com.szaruga.InternetBankingApplicationDemo.jpa;

import com.szaruga.InternetBankingApplicationDemo.bulider_entity.UserBuilder;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private final UserEntity testUserOne = UserBuilder.createTestUserOne();
    private final UserEntity testUserTwo = UserBuilder.createTestUserTwo();

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

        assertThat(usersList.size()).isEqualTo(2);
    }

    /**
     * Test case for {@link UserRepository#findById(Object)} to verify that the correct user is retrieved by ID.
     * This test checks if the repository returns the correct user by ID.
     */
    @Test
    public void userRepository_FindById_ReturnUser() {
        // Saving a test user into the repository
        UserEntity savedUser = userRepository.save(testUserOne);

        // Retrieving the saved user by ID
        Optional<UserEntity> userReturn = userRepository.findById(testUserOne.getId());

        // Assertions
        assertThat(userReturn).isPresent();
        assertThat(userReturn.get()).isEqualTo(savedUser);
    }

    /**
     * Test case for {@link UserRepository#delete(Object)} to verify that a user is successfully deleted from the repository.
     * This test checks if the repository correctly deletes a user and returns an empty repository.
     */
    @Test
    public void userRepository_Delete_ReturnEmptyRepository() {
        // Saving a test user into the repository
        userRepository.save(testUserOne);

        // Retrieving the saved user by ID
        Optional<UserEntity> userOptional = userRepository.findById(testUserOne.getId());
        assertThat(userOptional.isPresent());

        // Deleting user
        userRepository.delete(testUserOne);

        // Retrieving the user again after deletion
        Optional<UserEntity> userOptionalAfterDeletion = userRepository.findById(testUserOne.getId());

        // Assertions
        assertThat(userOptionalAfterDeletion).isEmpty();
    }
}


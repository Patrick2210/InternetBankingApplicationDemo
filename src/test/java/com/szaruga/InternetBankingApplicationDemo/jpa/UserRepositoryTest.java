package com.szaruga.InternetBankingApplicationDemo.jpa;

import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userRepository_SaveAll_ReturnSavedUser() {
        //Arrange
        UserEntity user = new UserEntity.Builder()
                .firstName("John")
                .lastName("Doe")
                .birthDate(LocalDate.now().minusYears(25))
                .numberPesel("1234567890")
                .email("john.doe@example.com")
                .phoneNumber("123-456-7890")
                .password("password123")
                .build();

        //Act
        UserEntity saveUser = userRepository.save(user);

        //Assert
        assertThat(saveUser).isNotNull();
        assertThat(saveUser.getId()).isGreaterThan(0);
    }
}

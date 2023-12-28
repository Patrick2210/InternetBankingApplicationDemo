package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.dto.UserDto;
import com.szaruga.InternetBankingApplicationDemo.dto.UserDtoUpdate;
import com.szaruga.InternetBankingApplicationDemo.entity.User;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import com.szaruga.InternetBankingApplicationDemo.model.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.validation.ValidationUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.USER_NOT_FOUND_WITH_ID;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final ValidationUserDto validationUserDto;

    @Autowired
    public UserService(UserRepository userRepository, ValidationUserDto validationUserDto) {
        this.userRepository = userRepository;
        this.validationUserDto = validationUserDto;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return optionalUser.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public void deleteUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else throw new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id);
    }

    public CreateUser saveUser(UserDto dto) {
        String firstName = dto.getFirstName();
        String lastName = dto.getLastName();
        String peselNumber = dto.getNumberPesel();
        LocalDate birthDate = dto.getBirthDate();
        String phoneNumber = dto.getPhoneNumber();
        String password = dto.getPassword();
        String email = dto.getEmail();

        validationUserDto.validateFirstName(firstName);
        validationUserDto.validateLastName(lastName);
        validationUserDto.validatePeselNumber(peselNumber);
        validationUserDto.validateBirthDate(birthDate);
        validationUserDto.validatePhoneNumber(phoneNumber);
        validationUserDto.validatePassword(password);
        validationUserDto.validateEmail(email);

        final User userEntity = convertDtoToEntity(dto);
        final User save = userRepository.save(userEntity);
        return new CreateUser(save.getId());
    }

    public void updateUser(UserDtoUpdate update) {
    }

    private User convertDtoToEntity(UserDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthDate(dto.getBirthDate());
        user.setNumberPesel(dto.getNumberPesel());
        user.setNumberPesel(dto.getNumberPesel());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(dto.getPassword());
        return user;
    }
}
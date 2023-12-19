package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.entity.User;
import com.szaruga.InternetBankingApplicationDemo.exception.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else throw new UserNotFoundException(USER_NOT_FOUND_WITH_ID.getMessage() + id);
    }
}
package com.szaruga.InternetBankingApplicationDemo.service;

import com.szaruga.InternetBankingApplicationDemo.entity.UserDetails;
import com.szaruga.InternetBankingApplicationDemo.exception.userdetails.UserDetailsNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.jpa.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.USER_DETAILS_NOT_FOUND_WITH_ID;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailsService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public List<UserDetails> findAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    public UserDetails findUserDetailsById(int id) {
        Optional<UserDetails> optionalUserDetails = userDetailsRepository.findById(id);
        Predicate<? super UserDetails> predicate = userDetails -> userDetails.getId().equals(id);
        return optionalUserDetails.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public UserDetails saveUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    public void deleteUserDetails(int id) {
        Optional<UserDetails> optionalUser = userDetailsRepository.findById(id);
        if (optionalUser.isPresent()) {
            userDetailsRepository.deleteById(id);
        } else throw new UserDetailsNotFoundException(USER_DETAILS_NOT_FOUND_WITH_ID.getMessage() + id);
    }
}

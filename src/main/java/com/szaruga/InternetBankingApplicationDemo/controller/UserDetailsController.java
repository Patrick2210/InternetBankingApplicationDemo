package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.GetUserDetailsByIdDto;
import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UserDetailsDto;
import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UsersDetailsPageDto;
import com.szaruga.InternetBankingApplicationDemo.model.userdetails.CreateUserDetails;
import com.szaruga.InternetBankingApplicationDemo.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/users/details/{pageNumber}/{pageSize}")
    public ResponseEntity<Map<String, Object>> retrievePageOfUserDetailsWithoutSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize) {
        Page<UsersDetailsPageDto> usersDetailsPage = userDetailsService.getAllUsersDetails(pageNumber, pageSize, null);
        Map<String, Object> response = new HashMap<>();
        response.put("usersDetails", usersDetailsPage.getContent());
        response.put("currentPage", usersDetailsPage.getNumber());
        response.put("totalItems", usersDetailsPage.getTotalElements());
        response.put("totalPages", usersDetailsPage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/details/{pageNumber}/{pageSize}/{sort}")
    public ResponseEntity<Map<String, Object>> retrievePageOfUserDetailsWithSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize,
            @PathVariable String sort) {
        Page<UsersDetailsPageDto> usersDetailsPage = userDetailsService.getAllUsersDetails(pageNumber, pageSize, sort);
        Map<String, Object> response = new HashMap<>();
        response.put("usersDetails", usersDetailsPage.getContent());
        response.put("currentPage", usersDetailsPage.getNumber());
        response.put("totalItems", usersDetailsPage.getTotalElements());
        response.put("totalPages", usersDetailsPage.getTotalPages());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/details/{id}")
    public ResponseEntity<GetUserDetailsByIdDto> retrieveUserDetailsById(@PathVariable int id) {
        return ResponseEntity.ok(userDetailsService.getUserDetailsById(id));
    }

    @PostMapping("/user/details")
    public ResponseEntity<CreateUserDetails> createUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
        return ResponseEntity.ok(userDetailsService.saveUserDetails(userDetailsDto));

    }

    @DeleteMapping("/user/details/{id}")
    public void deleteUserDetails(@PathVariable int id) {
        userDetailsService.deleteUserDetails(id);
    }
}
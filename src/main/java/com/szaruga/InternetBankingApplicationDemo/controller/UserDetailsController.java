package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UserDetailsDto;
import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UsersDetailsPageDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserDetailsEntity;
import com.szaruga.InternetBankingApplicationDemo.model.CreateUserDetails;
import com.szaruga.InternetBankingApplicationDemo.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/users-details/{pageNumber}/{pageSize}")
    public List<UsersDetailsPageDto> retrievePageOfUserDetailsWithoutSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize) {
        Page<UsersDetailsPageDto> data = userDetailsService.getUsersDetailsPagination(pageNumber, pageSize, null);
        return data.getContent();
    }

    @GetMapping("/users-details/{pageNumber}/{pageSize}/{sort}")
    public List<UsersDetailsPageDto> retrievePageOfUserDetailsWithSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize,
            @PathVariable String sort) {
        Page<UsersDetailsPageDto> data = userDetailsService.getUsersDetailsPagination(pageNumber, pageSize, sort);
        return data.getContent();
    }

    @GetMapping("/user/details/{id}")
    public EntityModel<UserDetailsEntity> retrieveUserDetailsById(@PathVariable int id) {
        //todo zrobic tylko zwort adress / homeNumber / flatNumber / postCode / city
        return null;
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

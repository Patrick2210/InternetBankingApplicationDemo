package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.dto.userdetails.UserDetailsDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserDetailsEntity;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
import com.szaruga.InternetBankingApplicationDemo.model.CreateUserDetails;
import com.szaruga.InternetBankingApplicationDemo.service.UserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.szaruga.InternetBankingApplicationDemo.constants.ApplicationConstants.USER_DETAILS_NOT_FOUND_WITH_ID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/user/details")
    public List<UserDetailsEntity> retrieveAllUserDetails() {
        return userDetailsService.findAllUserDetails();
    }

    @GetMapping("/user/details/{id}")
    public EntityModel<UserDetailsEntity> retrieveUserDetailsById(@PathVariable int id) {
        //todo cala logic wyjebac
        UserDetailsEntity userDetailsEntity = userDetailsService.findUserDetailsById(id);
        if (userDetailsEntity == null) {
            throw new UserNotFoundException(USER_DETAILS_NOT_FOUND_WITH_ID.getMessage() + id);
        }
        EntityModel<UserDetailsEntity> entityModel = EntityModel.of(userDetailsEntity);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUserDetails());
        entityModel.add(link.withRel("all-users-details"));
        return entityModel;
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

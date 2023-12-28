package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.entity.UserDetails;
import com.szaruga.InternetBankingApplicationDemo.exception.user.UserNotFoundException;
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
    public List<UserDetails> retrieveAllUserDetails() {
        return userDetailsService.findAllUserDetails();
    }

    @GetMapping("/user/details/{id}")
    public EntityModel<UserDetails> retrieveUserDetailsById(@PathVariable int id) {
        UserDetails userDetails = userDetailsService.findUserDetailsById(id);
        if (userDetails == null) {
            throw new UserNotFoundException(USER_DETAILS_NOT_FOUND_WITH_ID.getMessage() + id);
        }
        EntityModel<UserDetails> entityModel = EntityModel.of(userDetails);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUserDetails());
        entityModel.add(link.withRel("all-users-details"));
        return entityModel;
    }

    @PostMapping("/user/details")
    public ResponseEntity<UserDetails> createUserDetails(@Valid @RequestBody UserDetails userDetails) {
        UserDetails savedUserDetails = userDetailsService.saveUserDetails(userDetails);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUserDetails)
                .toUri();
        return ResponseEntity.created(location).build();
        //TODO ask master how to set up user_id when POST Json file
    }

    @DeleteMapping("/user/details/{id}")
    public void deleteUserDetails(@PathVariable int id) {
        userDetailsService.deleteUserDetails(id);
    }
}

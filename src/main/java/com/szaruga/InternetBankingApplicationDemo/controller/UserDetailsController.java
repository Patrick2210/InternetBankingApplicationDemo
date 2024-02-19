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

/**
 * Controller class for managing user details operations.
 */
@RestController
@RequestMapping("/api")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    /**
     * Constructs an instance of UserDetailsController.
     *
     * @param userDetailsService The service for managing user details.
     */
    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Formats the response page for user details.
     *
     * @param page The page of user details.
     * @return A formatted map representing the response.
     */
    private Object responsePage(Page<UsersDetailsPageDto> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("users", page.getContent());
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return response;
    }

    /**
     * Retrieves a page of user details without sorting.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The size of each page.
     * @return ResponseEntity containing the page of user details.
     */
    @GetMapping("/users/details/{pageNumber}/{pageSize}")
    public ResponseEntity<Object> retrievePageOfUserDetailsWithoutSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize) {
        Page<UsersDetailsPageDto> usersDetailsPage = userDetailsService.getAllUsersDetails(pageNumber, pageSize, null);
        return ResponseEntity.ok(responsePage(usersDetailsPage));
    }

    /**
     * Retrieves a page of user details with sorting.
     *
     * @param pageNumber The page number to retrieve.
     * @param pageSize   The size of each page.
     * @param sort       The field to sort by.
     * @return ResponseEntity containing the page of user details.
     */
    @GetMapping("/users/details/{pageNumber}/{pageSize}/{sort}")
    public ResponseEntity<Object> retrievePageOfUserDetailsWithSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize,
            @PathVariable String sort) {
        Page<UsersDetailsPageDto> usersDetailsPage = userDetailsService.getAllUsersDetails(pageNumber, pageSize, sort);
        return ResponseEntity.ok(responsePage(usersDetailsPage));
    }

    /**
     * Retrieves user details by ID.
     *
     * @param id The ID of the user details to retrieve.
     * @return ResponseEntity containing the user details.
     */
    @GetMapping("/users/details/{id}")
    public ResponseEntity<GetUserDetailsByIdDto> retrieveUserDetailsById(@PathVariable int id) {
        return ResponseEntity.ok(userDetailsService.getUserDetailsById(id));
    }

    /**
     * Creates user details.
     *
     * @param userDetailsDto The DTO containing user details information.
     * @return ResponseEntity containing the ID of the created user details.
     */
    @PostMapping("/users/details")
    public ResponseEntity<CreateUserDetails> createUserDetails(@RequestBody UserDetailsDto userDetailsDto) {
        return ResponseEntity.ok(userDetailsService.saveUserDetails(userDetailsDto));

    }

    /**
     * Deletes user details by ID.
     *
     * @param id The ID of the user details to delete.
     */
    @DeleteMapping("/users/details/{id}")
    public void deleteUserDetails(@PathVariable int id) {
        userDetailsService.deleteUserDetails(id);
    }
}
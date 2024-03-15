package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.dto.user.*;
import com.szaruga.InternetBankingApplicationDemo.model.user.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for managing user-related operations.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    /**
     * Constructs an instance of the UserController.
     *
     * @param userService The service for managing user-related operations.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Helper method to create a response object for paginated data.
     *
     * @param page The page object containing user data.
     * @return A response object containing paginated user data.
     */
    private Map<String, Object> responsePage(Page<UsersPageDto> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("users", page.getContent());
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return response;
    }

    /**
     * Retrieves a page of users without sorting.
     *
     * @param pageNumber The page number.
     * @param pageSize   The size of each page.
     * @return ResponseEntity containing paginated user data.
     */
    @GetMapping("/users/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<UsersPageDto>> retrievePageOfUsersWithoutSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize) {
        Page<UsersPageDto> usersDto = userService.getAllUsers(pageNumber, pageSize, null);
        return ResponseEntity.ok(usersDto);
    }

    /**
     * Retrieves a page of users with sorting.
     *
     * @param pageNumber The page number.
     * @param pageSize   The size of each page.
     * @param sort       The field to sort by.
     * @return ResponseEntity containing paginated user data.
     */
    @GetMapping("/users/{pageNumber}/{pageSize}/{sort}")
    public ResponseEntity<Object> retrievePageOfUsersWithSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String sort) {
        Page<UsersPageDto> usersDto = userService.getAllUsers(pageNumber, pageSize, sort);
        return ResponseEntity.ok(responsePage(usersDto));
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return ResponseEntity containing the user data.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<GetUserByIdDto> retrieveUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Creates a new user.
     *
     * @param userDto The DTO containing user information.
     * @return ResponseEntity containing the ID of the created user.
     */
    @PostMapping("/users")
    public ResponseEntity<CreateUser> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete.
     */
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    /**
     * Updates a user's information.
     *
     * @param id     The ID of the user to update.
     * @param update The DTO containing updated user information.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable long id,
            @RequestBody UserUpdateDto update
    ) {
        userService.updateUser(id, update);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates a user's password.
     *
     * @param id                The ID of the user whose password to update.
     * @param updatePasswordDto The DTO containing the new password.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PutMapping("/users/{id}/password-change")
    public ResponseEntity<Void> updateUserPassword(
            @PathVariable long id,
            @RequestBody UserPasswordUpdateDto updatePasswordDto
    ) {
        userService.updateUserPassword(id, updatePasswordDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Updates a user's email address.
     *
     * @param id          The ID of the user whose email to update.
     * @param emailUpdate The DTO containing the new email address.
     * @return ResponseEntity indicating the success of the operation.
     */
    @PutMapping("/users/{id}/email-change")
    public ResponseEntity<Void> updateUserEmail(
            @PathVariable long id,
            @RequestBody UserEmailUpdateDto emailUpdate
    ) {
        userService.updateUserEmail(id, emailUpdate);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/reset-email")
    public ResponseEntity<Void> resetUserEmail() {
        //todo skonczyc to
        return ResponseEntity.ok().build();
    }
}
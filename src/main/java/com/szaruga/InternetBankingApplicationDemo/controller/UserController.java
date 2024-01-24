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

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private Object responsePage(Page<UsersPageDto> page) {
        Map<String, Object> response = new HashMap<>();
        response.put("users", page.getContent());
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return response;
    }

    @GetMapping("/users/{pageNumber}/{pageSize}")
    public ResponseEntity<Object> retrievePageOfUsersWithoutSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize) {
        Page<UsersPageDto> usersDto = userService.getAllUsers(pageNumber, pageSize, null);
        return ResponseEntity.ok(responsePage(usersDto));
    }

    @GetMapping("/users/{pageNumber}/{pageSize}/{sort}")
    public ResponseEntity<Object> retrievePageOfUsersWithSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String sort) {
        Page<UsersPageDto> usersDto = userService.getAllUsers(pageNumber, pageSize, sort);
        return ResponseEntity.ok(responsePage(usersDto));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<GetUserByIdDto> retrieveUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<CreateUser> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable long id,
            @RequestBody UserUpdateDto update
    ) {
        userService.updateUser(id, update);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{id}/password-change")
    public ResponseEntity<Void> updateUserPassword(
            @PathVariable long id,
            @RequestBody UserPasswordUpdateDto updatePasswordDto
    ) {
        userService.updateUserPassword(id, updatePasswordDto);
        return ResponseEntity.ok().build();
    }
}
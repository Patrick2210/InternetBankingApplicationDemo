package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.dto.user.*;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.model.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{pageNumber}/{pageSize}")
    public List<UsersPageDto> retrievePageOfUsersWithoutSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize) {
        Page<UsersPageDto> data = userService.getUsersPagination(pageNumber, pageSize, null);
        return data.getContent();
    }

    @GetMapping("/users/{pageNumber}/{pageSize}/{sort}")
    public List<UsersPageDto> retrievePageOfUsersWithSorting(
            @PathVariable Integer pageNumber,
            @PathVariable Integer pageSize,
            @PathVariable String sort) {
        Page<UsersPageDto> data = userService.getUsersPagination(pageNumber, pageSize, sort);
        return data.getContent();
    }

    @GetMapping("/users/{id}")
    public EntityModel<UserPageDto> retrievePageOfUserById(@PathVariable long id) {
        return EntityModel.of(userService.getPageOfUserById(id));
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
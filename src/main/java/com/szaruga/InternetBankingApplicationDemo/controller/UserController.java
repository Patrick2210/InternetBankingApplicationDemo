package com.szaruga.InternetBankingApplicationDemo.controller;

import com.szaruga.InternetBankingApplicationDemo.dto.user.UserPasswordUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.dto.user.UserDto;
import com.szaruga.InternetBankingApplicationDemo.dto.user.UserUpdateDto;
import com.szaruga.InternetBankingApplicationDemo.entity.UserEntity;
import com.szaruga.InternetBankingApplicationDemo.model.CreateUser;
import com.szaruga.InternetBankingApplicationDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserEntity> retrieveAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/user/{id}")
    public EntityModel<UserEntity> retrieveUserById(@PathVariable long id) {
        return EntityModel.of(userService.findUserById(id));
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUser> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable long id,
            @RequestBody UserUpdateDto update) {
        userService.updateUser(id, update);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/{id}/password-change")
    public ResponseEntity<Void> updateUserPassword(
            @PathVariable long id,
            @RequestBody UserPasswordUpdateDto updatePasswordDto) {
        userService.updateUserPassword(id, updatePasswordDto);
        return ResponseEntity.ok().build();
    }
}

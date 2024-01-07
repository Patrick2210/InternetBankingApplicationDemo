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
        //Todo zrobic paginacja
        return userService.findAllUsers();
        /**
         * wjebac to i uzytkownik musi w parametrze podac pagerequest i wtedy ma mi sie wyswietlic np. 1-10 potem 11-20 potem 21-30 itp
         * public static PageRequest of(int pageNumber, int pageSize) {
         *         return of(pageNumber, pageSize, Sort.unsorted());
         *     }
         * */
    }

    @GetMapping("/users/{id}")
    public EntityModel<UserEntity> retrieveUserById(@PathVariable long id) {
        //TOdo userEnityt out i zrobic custom object
        return EntityModel.of(userService.findUserById(id));
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

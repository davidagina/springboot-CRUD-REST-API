package com.daveproject.springboot.controller;

import com.daveproject.springboot.entity.User;
import com.daveproject.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    // Build create user REST API
    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser =  userService.creatUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // Build get user by id REST API
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long userId){
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

}

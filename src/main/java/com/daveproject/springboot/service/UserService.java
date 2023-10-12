package com.daveproject.springboot.service;

import com.daveproject.springboot.dto.UserDto;
import com.daveproject.springboot.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUser(Long id);
}

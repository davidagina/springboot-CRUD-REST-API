package com.daveproject.springboot.service;

import com.daveproject.springboot.entity.User;

import java.util.List;

public interface UserService {

    User creatUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();
}

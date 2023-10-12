package com.daveproject.springboot.service.impl;

import com.daveproject.springboot.entity.User;
import com.daveproject.springboot.repository.UserRepository;
import com.daveproject.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    @Override
    public User creatUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}

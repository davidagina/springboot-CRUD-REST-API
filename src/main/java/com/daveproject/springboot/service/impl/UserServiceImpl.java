package com.daveproject.springboot.service.impl;

import com.daveproject.springboot.dto.UserDto;
import com.daveproject.springboot.entity.User;
import com.daveproject.springboot.mapper.UserMapper;
import com.daveproject.springboot.repository.UserRepository;
import com.daveproject.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        // Convert userDto to user JPA entity object
        User user = UserMapper.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        // Convert user JPA entity into userDto
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);


        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id).get();

       return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser =  userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

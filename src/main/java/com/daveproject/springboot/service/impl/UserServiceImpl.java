package com.daveproject.springboot.service.impl;

import com.daveproject.springboot.dto.UserDto;
import com.daveproject.springboot.entity.User;
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
        User user = new User(userDto.getId(), userDto.getFirstName(),
                userDto.getLastName(), userDto.getEmail());

        User savedUser = userRepository.save(user);

        // Convert user JPA entity into userDto
        UserDto savedUserDto = new UserDto(user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail());

        return savedUserDto;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
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

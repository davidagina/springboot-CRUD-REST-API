package com.daveproject.springboot.service.impl;

import com.daveproject.springboot.dto.UserDto;
import com.daveproject.springboot.entity.User;
import com.daveproject.springboot.exception.ResourceNotFoundException;
import com.daveproject.springboot.mapper.AutoUserMapper;
import com.daveproject.springboot.mapper.UserMapper;
import com.daveproject.springboot.repository.UserRepository;
import com.daveproject.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        // Convert userDto to user JPA entity object
//        User user = UserMapper.mapToUser(userDto);
//        User user = modelMapper.map(userDto, User.class);
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        // Convert user JPA entity into userDto
//        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
//        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);


        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

//       return UserMapper.mapToUserDto(user);
//        return modelMapper.map(user, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());
//        return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());

        return users.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {

        User existingUser = userRepository.findById(user.getId())
                .orElseThrow( () -> new ResourceNotFoundException("User", "id", user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updatedUser);
//        return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        userRepository.deleteById(id);
    }
}

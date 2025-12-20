package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

import java.util.Optional;

public interface UserService {
    User register(UserDto userDto);
    Optional<User> findByEmail(String email);
}

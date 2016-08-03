package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.Role;
import com.cpcs.restaurant.entity.User;
import com.cpcs.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    private static final Role DEFAULT_USER_ROLE = new Role(2L, "user");
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(String username, String password) {
        userRepository.save(new User(username, PASSWORD_ENCODER.encode(password), DEFAULT_USER_ROLE));
    }

}

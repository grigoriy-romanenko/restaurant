package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.Role;
import com.cpcs.restaurant.entity.User;
import com.cpcs.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Role DEFAULT_USER_ROLE = new Role(2L, "user");
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private static final String INVALID_USERNAME_MSG = "Registration failed. Username is already in use";

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException(INVALID_USERNAME_MSG);
        }
        User user = new User(username, PASSWORD_ENCODER.encode(password), DEFAULT_USER_ROLE);
        userRepository.save(user);
    }

}

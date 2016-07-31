package com.cpcs.restaurant.controller;

import com.cpcs.restaurant.entity.Role;
import com.cpcs.restaurant.entity.User;
import com.cpcs.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private static final Role DEFAULT_USER_ROLE = new Role(2L, "user");
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public String profile() {
        return "profile";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password) {
        userRepository.save(new User(username, PASSWORD_ENCODER.encode(password), DEFAULT_USER_ROLE));
    }

}
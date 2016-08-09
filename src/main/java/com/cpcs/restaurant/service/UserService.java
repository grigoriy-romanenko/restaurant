package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.User;

public interface UserService {

    User getUser(String username);
    void register(String username, String password);

}
package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.Cart;

public interface CartService {

    Cart getCart(String username);
    void addToCart(String username, Long menuItemId);
    void deleteFromCart(String username, Long menuItemId);

}
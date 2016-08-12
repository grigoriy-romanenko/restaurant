package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.Cart;
import com.cpcs.restaurant.entity.Order;

import java.util.List;

public interface OrderService {

    Cart getCart(String username);
    void addToCart(String username, Long menuItemId);
    void deleteFromCart(String username, Long menuItemId);
    void purchase(String username);
    List<Order> getOrders();

}
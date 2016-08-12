package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.Cart;
import com.cpcs.restaurant.entity.MenuItem;
import com.cpcs.restaurant.entity.User;
import com.cpcs.restaurant.repository.CartRepository;
import com.cpcs.restaurant.repository.MenuItemRepository;
import com.cpcs.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    @PreAuthorize("#username == authentication.name")
    public Cart getCart(String username) {
        Cart cart = cartRepository.findByUsername(username);
        if (cart == null) {
            User user = userRepository.findByUsername(username);
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    @PreAuthorize("#username == authentication.name")
    public void addToCart(String username, Long menuItemId) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(menuItemId);
        getCart(username).getMenuItems().add(menuItem);
    }

    @Override
    @PreAuthorize("#username == authentication.name")
    public void deleteFromCart(String username, Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findOne(menuItemId);
        getCart(username).getMenuItems().remove(menuItem);
    }

    @Override
    @PreAuthorize("#username == authentication.name")
    public void purchase(String username) {

    }
    
}
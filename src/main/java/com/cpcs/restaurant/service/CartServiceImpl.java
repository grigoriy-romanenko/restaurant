package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.Cart;
import com.cpcs.restaurant.entity.MenuItem;
import com.cpcs.restaurant.entity.User;
import com.cpcs.restaurant.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;

    @Override
    @PreAuthorize("#username == authentication.name")
    public Cart getCart(String username) {
        Cart cart = cartRepository.findByUsername(username);
        if (cart == null) {
            User user = userService.getUser(username);
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

}
package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.*;
import com.cpcs.restaurant.repository.CartRepository;
import com.cpcs.restaurant.repository.MenuItemRepository;
import com.cpcs.restaurant.repository.OrderRepository;
import com.cpcs.restaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private CartRepository cartRepository;
    private UserRepository userRepository;
    private MenuItemRepository menuItemRepository;
    private OrderRepository orderRepository;

    @Autowired
    public void setCartRepository(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMenuItemRepository(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

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
        Cart cart = getCart(username);
        User user = userRepository.findByUsername(username);
        Order order = new Order();
        order.setUser(user);
        order.setDate(new Date());
        order.setOrderItems(
                cart.getMenuItems().stream().map(menuItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setMenuItem(menuItem);
                    orderItem.setPrice(menuItem.getPrice());
                    return orderItem;
                }).collect(Collectors.toList())
        );
        orderRepository.save(order);
        cart.getMenuItems().clear();
        cartRepository.save(cart);
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

}
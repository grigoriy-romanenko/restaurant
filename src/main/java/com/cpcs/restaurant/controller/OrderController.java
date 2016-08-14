package com.cpcs.restaurant.controller;

import com.cpcs.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/users/{username}/cart", method = RequestMethod.GET)
    public String cart(@PathVariable("username") String username, Model model) {
        model.addAttribute("cart", orderService.getCart(username));
        return "cart";
    }

    @RequestMapping(value = "/users/{username}/cart", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addToCart(@PathVariable("username") String username, @RequestParam Long menuItemId) {
        orderService.addToCart(username, menuItemId);
    }

    @RequestMapping(value = "/users/{username}/cart/{menuItemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteFromCart(@PathVariable("username") String username, @PathVariable("menuItemId") Long menuItemId) {
        orderService.deleteFromCart(username, menuItemId);
    }

    @RequestMapping(value = "/users/{username}/cart/purchase", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void purchase(@PathVariable("username") String username) {
        orderService.purchase(username);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        return "orders";
    }

}
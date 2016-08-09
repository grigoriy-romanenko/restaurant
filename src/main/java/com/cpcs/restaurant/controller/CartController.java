package com.cpcs.restaurant.controller;

import com.cpcs.restaurant.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/users/{username}/cart", method = RequestMethod.GET)
    public String cart(@PathVariable("username") String username, Model model) {
        model.addAttribute("cart", cartService.getCart(username));
        return "cart";
    }

    @RequestMapping(value = "/users/{username}/cart", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void addToCart(@PathVariable("username") String username, @RequestParam Long menuItemId) {
        cartService.addToCart(username, menuItemId);
    }

}
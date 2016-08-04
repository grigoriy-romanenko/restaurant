package com.cpcs.restaurant.controller;

import com.cpcs.restaurant.entity.Category;
import com.cpcs.restaurant.entity.MenuItem;
import com.cpcs.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String menu() {
        return "menu";
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String menuItem(@PathVariable("id") Long menuItemId, Model model) {
        MenuItem menuItem = menuService.getMenuItem(menuItemId);
        model.addAttribute("menuItem", menuItem);
        return "menuItem";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Category> getCategories() {
        return menuService.getCategories();
    }

    @RequestMapping(value = "/categories/{id}/items", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<MenuItem> getMenuItems(@PathVariable("id") Long categoryId) {
        return menuService.getMenuItems(categoryId);
    }

    @RequestMapping(value = "/categories/{id}/items", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void createMenuItemForm(@PathVariable("id") Long categoryId, @RequestBody MenuItem menuItem) {
        Category category = new Category();
        category.setId(categoryId);
        menuItem.setCategory(category);
        menuService.createMenuItem(menuItem);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void editMenuItemForm(@PathVariable("id") Long menuItemId, @RequestBody MenuItem menuItem) {
        menuItem.setId(menuItemId);
        menuService.editMenuItem(menuItem);
    }

}
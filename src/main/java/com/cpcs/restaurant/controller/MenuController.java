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

    private MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String menu() {
        return "redirect:/menu/categories/1";
    }

    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.GET)
    public String category(@PathVariable("categoryId") Long categoryId, Model model) {
        Category category = menuService.getCategory(categoryId);
        model.addAttribute("category", category);
        return "category";
    }

    @RequestMapping(value = "/items/{menuItemId}", method = RequestMethod.GET)
    public String menuItem(@PathVariable("menuItemId") Long menuItemId, Model model) {
        MenuItem menuItem = menuService.getMenuItem(menuItemId);
        model.addAttribute("menuItem", menuItem);
        return "menuItem";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Category> getCategories() {
        return menuService.getCategories();
    }

    @RequestMapping(value = "/categories/{id}/items", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createMenuItem(@PathVariable("id") Long categoryId, @RequestBody MenuItem menuItem) {
        menuItem.setCategory(new Category(categoryId));
        menuService.createMenuItem(menuItem);
    }

    @RequestMapping(value = "/items/{menuItemId}", method = RequestMethod.PUT, consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void editMenuItem(@PathVariable("menuItemId") Long menuItemId, @RequestBody MenuItem menuItem) {
        menuItem.setId(menuItemId);
        menuService.editMenuItem(menuItem);
    }

    @RequestMapping(value = "/items/{menuItemId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteMenuItem(@PathVariable("menuItemId") Long menuItemId) {
        menuService.deleteMenuItem(menuItemId);
    }

}
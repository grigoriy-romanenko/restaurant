package com.cpcs.restaurant.controller;

import com.cpcs.restaurant.entity.Category;
import com.cpcs.restaurant.entity.MenuItem;
import com.cpcs.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Category> getCategories() {
        return menuService.getCategories();
    }

    @RequestMapping(value = "/categories/{id}/items", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<MenuItem> getMenuItems(@PathVariable("id") Long categoryId) {
        return menuService.getMenuItems(categoryId);
    }

}
package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.Category;
import com.cpcs.restaurant.entity.MenuItem;

import java.util.List;

public interface MenuService {

    MenuItem getMenuItem(Long menuItemId);
    List<MenuItem> getMenuItems(Long categoryId);
    List<Category> getCategories();
    void createMenuItem(MenuItem menuItem);
    void editMenuItem(MenuItem menuItem);

}
package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.Category;
import com.cpcs.restaurant.entity.MenuItem;
import com.cpcs.restaurant.repository.CategoryRepository;
import com.cpcs.restaurant.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuServiceImpl implements MenuService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItem> getMenuItems(Long categoryId) {
        return categoryRepository.findOne(categoryId).getMenuItems();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createMenuItem(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

}
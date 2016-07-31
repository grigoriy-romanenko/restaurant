package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.Category;
import com.cpcs.restaurant.entity.MenuItem;
import com.cpcs.restaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuServiceImpl implements MenuService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<MenuItem> getMenuItems(Long categoryId) {
        return categoryRepository.findOne(categoryId).getMenuItems();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

}
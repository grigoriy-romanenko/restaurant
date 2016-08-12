package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.Category;
import com.cpcs.restaurant.entity.MenuItem;
import com.cpcs.restaurant.repository.CategoryRepository;
import com.cpcs.restaurant.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private CategoryRepository categoryRepository;
    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuServiceImpl(CategoryRepository categoryRepository, MenuItemRepository menuItemRepository) {
        this.categoryRepository = categoryRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem getMenuItem(Long menuItemId) {
        return menuItemRepository.findOne(menuItemId);
    }

    @Override
    public Category getCategory(Long categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Override
    public List<MenuItem> getMenuItems(Long categoryId) {
        return categoryRepository.findOne(categoryId).getMenuItems();
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    public void createMenuItem(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    public void editMenuItem(MenuItem menuItem) {
        if (!menuItemRepository.exists(menuItem.getId())) {
            throw new IllegalArgumentException("Menu item does not exist. " + menuItem);
        }
        menuItemRepository.save(menuItem);
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    public void deleteMenuItem(Long menuItemId) {
        menuItemRepository.delete(menuItemId);
    }

}
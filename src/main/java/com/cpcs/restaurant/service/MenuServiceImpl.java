package com.cpcs.restaurant.service;

import com.cpcs.restaurant.entity.Category;
import com.cpcs.restaurant.entity.MenuItem;
import com.cpcs.restaurant.repository.CategoryRepository;
import com.cpcs.restaurant.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    private static final String MENU_ITEM_NOT_FOUND_MSG = "Menu item does not exist. ID: ";
    private static final String CATEGORY_NOT_FOUND_MSG = "Category does not exist. ID: ";
    private static final String INVALID_MENU_ITEM_TITLE_MSG = "Given menu item title is already in use. Title: ";

    private CategoryRepository categoryRepository;
    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuServiceImpl(CategoryRepository categoryRepository, MenuItemRepository menuItemRepository) {
        this.categoryRepository = categoryRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem getMenuItem(Long menuItemId) {
        MenuItem menuItem = menuItemRepository.findOne(menuItemId);
        if (menuItem == null) {
            throw new NoSuchElementException(MENU_ITEM_NOT_FOUND_MSG + menuItemId);
        }
        return menuItem;
    }

    @Override
    public Category getCategory(Long categoryId) {
        Category category = categoryRepository.findOne(categoryId);
        if (category == null) {
            throw new NoSuchElementException(CATEGORY_NOT_FOUND_MSG + categoryId);
        }
        return category;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    public void createMenuItem(MenuItem menuItem) {
        if (menuItemRepository.findByTitle(menuItem.getTitle()) != null) {
            throw new RestClientException(INVALID_MENU_ITEM_TITLE_MSG + menuItem.getTitle());
        }
        menuItemRepository.save(menuItem);
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    public void editMenuItem(MenuItem menuItem) {
        if (!menuItemRepository.exists(menuItem.getId())) {
            throw new RestClientException(MENU_ITEM_NOT_FOUND_MSG + menuItem.getId());
        }
        MenuItem existed = menuItemRepository.findByTitle(menuItem.getTitle());
        if (existed != null && !existed.getId().equals(menuItem.getId())) {
            throw new RestClientException(INVALID_MENU_ITEM_TITLE_MSG + menuItem.getTitle());
        }
        menuItemRepository.save(menuItem);
    }

    @Override
    @PreAuthorize("hasAuthority('admin')")
    public void deleteMenuItem(Long menuItemId) {
        menuItemRepository.delete(menuItemId);
    }

}
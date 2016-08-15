package com.cpcs.restaurant.controller;

import com.cpcs.restaurant.config.ApplicationConfig;
import com.cpcs.restaurant.config.SecurityConfig;
import com.cpcs.restaurant.config.WebConfig;
import com.cpcs.restaurant.entity.Category;
import com.cpcs.restaurant.entity.MenuItem;
import com.cpcs.restaurant.service.MenuService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;

import static org.springframework.http.MediaType.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, WebConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class MenuControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String MENU_ITEMS_TABLE_NAME = "menuitems";
    private static final String CREATE_MENU_ITEM_JSON_TEMPLATE = "{\"title\": \"%s\", \"price\": %s}";
    private static final String EDIT_MENU_ITEM_JSON_TEMPLATE = "{\"title\": \"%s\", \"price\": %s, \"category\": {\"id\": %s}}";
    private static final String CREATE_MENU_ITEM_WHERE_TEMPLATE = "title = '%s' and price = %s and category = %s";
    private static final String CREATE_MENU_ITEM_URL_TEMPLATE = "/menu/categories/%s/items";

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MenuService menuService;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void testGetCategory() throws Exception {
        Category category = menuService.getCategory(2L);
        mockMvc.perform(get("/menu/categories/2"))
                .andExpect(model().attribute("category", category))
                .andExpect(view().name("category"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCategoryWithNotExistingId() throws Exception {
        mockMvc.perform(get("/menu/categories/0")).andExpect(status().isNotFound());
    }

    @Test
    public void testGetMenuItem() throws Exception {
        MenuItem menuItem = menuService.getMenuItem(2L);
        mockMvc.perform(get("/menu/items/2"))
                .andExpect(model().attribute("menuItem", menuItem))
                .andExpect(view().name("menuItem"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMenuItemWithNotExistingId() throws Exception {
        mockMvc.perform(get("/menu/items/0")).andExpect(status().isNotFound());
    }

    @Test
    public void testGetCategories() throws Exception {
        String expectedResponseBody = new ObjectMapper().writeValueAsString(menuService.getCategories());
        mockMvc.perform(get("/menu/categories"))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(content().json(expectedResponseBody))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateMenuItem() throws Exception {
        String title = "New";
        Long price = 1L;
        Long categoryId = 1L;
        String requestBody = String.format(CREATE_MENU_ITEM_JSON_TEMPLATE, title, price);
        String where = String.format(CREATE_MENU_ITEM_WHERE_TEMPLATE, title, price, categoryId);
        int menuItemsCountBefore = countRowsInTableWhere(MENU_ITEMS_TABLE_NAME, where);
        String url = String.format(CREATE_MENU_ITEM_URL_TEMPLATE, categoryId);
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(requestBody).session(authenticate()))
                .andExpect(status().isCreated());
        int menuItemsCountAfter = countRowsInTableWhere(MENU_ITEMS_TABLE_NAME, where);
        assertEquals(0, menuItemsCountBefore);
        assertEquals(1, menuItemsCountAfter);
    }

    @Test
    public void testCreateMenuItemWithoutAuthentication() throws Exception {
        String title = "New";
        Long price = 1L;
        Long categoryId = 1L;
        String requestBody = String.format(CREATE_MENU_ITEM_JSON_TEMPLATE, title, price);
        String url = String.format(CREATE_MENU_ITEM_URL_TEMPLATE, categoryId);
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(requestBody))
                .andExpect(status().isForbidden());
        String where = String.format(CREATE_MENU_ITEM_WHERE_TEMPLATE, title, price, categoryId);
        assertEquals(0, countRowsInTableWhere(MENU_ITEMS_TABLE_NAME, where));
    }

    @Test
    public void testCreateMenuItemWithInvalidTitle() throws Exception {
        String invalidTitle = " ";
        Long price = 1L;
        Long categoryId = 1L;
        String requestBody = String.format(CREATE_MENU_ITEM_JSON_TEMPLATE, invalidTitle, price);
        String url = String.format(CREATE_MENU_ITEM_URL_TEMPLATE, categoryId);
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(requestBody).session(authenticate()))
                .andExpect(status().isBadRequest());
        String where = String.format(CREATE_MENU_ITEM_WHERE_TEMPLATE, invalidTitle, price, categoryId);
        assertEquals(0, countRowsInTableWhere(MENU_ITEMS_TABLE_NAME, where));
    }

    @Test
    public void testCreateMenuItemWithNotUniqueTitle() throws Exception {
        String notUniqueTitle = menuService.getMenuItem(1L).getTitle();
        Long price = 1L;
        Long categoryId = 1L;
        String requestBody = String.format(CREATE_MENU_ITEM_JSON_TEMPLATE, notUniqueTitle, price);
        String url = String.format(CREATE_MENU_ITEM_URL_TEMPLATE, categoryId);
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(requestBody).session(authenticate()))
                .andExpect(status().isBadRequest());
        String where = String.format(CREATE_MENU_ITEM_WHERE_TEMPLATE, notUniqueTitle, price, categoryId);
        assertEquals(0, countRowsInTableWhere(MENU_ITEMS_TABLE_NAME, where));
    }

    @Test
    public void testCreateMenuItemWithInvalidPrice() throws Exception {
        String title = "New";
        Long invalidPrice = 0L;
        Long categoryId = 1L;
        String requestBody = String.format(CREATE_MENU_ITEM_JSON_TEMPLATE, title, invalidPrice);
        String url = String.format(CREATE_MENU_ITEM_URL_TEMPLATE, categoryId);
        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8).content(requestBody).session(authenticate()))
                .andExpect(status().isBadRequest());
        String where = String.format(CREATE_MENU_ITEM_WHERE_TEMPLATE, title, invalidPrice, categoryId);
        assertEquals(0, countRowsInTableWhere(MENU_ITEMS_TABLE_NAME, where));
    }

    @Test
    public void testEditMenuItem() throws Exception {
        Long menuItemId = 1L;
        String updatedTitle = "Updated";
        Long updatedPrice = 1L;
        Long updatedCategoryId = 2L;
        String requestBody = String.format(EDIT_MENU_ITEM_JSON_TEMPLATE, updatedTitle, updatedPrice, updatedCategoryId);
        MenuItem menuItemBefore = menuService.getMenuItem(menuItemId);
        assertNotEquals(updatedTitle, menuItemBefore.getTitle());
        assertNotEquals(updatedPrice, menuItemBefore.getPrice());
        assertNotEquals(updatedCategoryId, menuItemBefore.getCategory().getId());
        mockMvc.perform(put("/menu/items/" + menuItemId)
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestBody)
                        .session(authenticate()))
                .andExpect(status().isOk());
        MenuItem menuItemAfter = menuService.getMenuItem(menuItemId);
        assertEquals(updatedTitle, menuItemAfter.getTitle());
        assertEquals(updatedPrice, menuItemAfter.getPrice());
        assertEquals(updatedCategoryId, menuItemAfter.getCategory().getId());
    }

    @Test
    public void testEditMenuItemWithNotExistingId() throws Exception {
        String updatedTitle = "Updated";
        Long updatedPrice = 1L;
        Long updatedCategoryId = 2L;
        String requestBody = String.format(EDIT_MENU_ITEM_JSON_TEMPLATE, updatedTitle, updatedPrice, updatedCategoryId);
        mockMvc.perform(put("/menu/items/0")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestBody)
                        .session(authenticate()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteMenuItem() throws Exception {
        assertEquals(1, countRowsInTableWhere(MENU_ITEMS_TABLE_NAME, "id = 1"));
        mockMvc.perform(delete("/menu/items/1").session(authenticate())).andExpect(status().isOk());
        entityManager.flush();
        assertEquals(0, countRowsInTableWhere(MENU_ITEMS_TABLE_NAME, "id = 1"));
    }

    private MockHttpSession authenticate() throws Exception {
        return (MockHttpSession) mockMvc.perform(formLogin().user("admin").password("admin"))
                .andExpect(authenticated())
                .andReturn().getRequest().getSession();
    }

}
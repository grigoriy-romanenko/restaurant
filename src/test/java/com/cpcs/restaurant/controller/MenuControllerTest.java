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

    @Autowired
    EntityManager entityManager;
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
        String requestBody = "{\"title\": \"New\", \"price\": 1}";
        int menuItemsCountBefore = countRowsInTableWhere("menuitems", "title = 'New' and price = 1 and category = 1");
        mockMvc.perform(post("/menu/categories/1/items")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestBody)
                        .session(authenticate()))
                .andExpect(status().isCreated());
        int menuItemsCountAfter = countRowsInTableWhere("menuitems", "title = 'New' and price = 1 and category = 1");
        assertEquals(0, menuItemsCountBefore);
        assertEquals(1, menuItemsCountAfter);
    }

    @Test
    public void testCreateMenuItemWithoutAuthentication() throws Exception {
        String requestBody = "{\"title\": \"New\", \"price\": 1}";
        mockMvc.perform(post("/menu/categories/1/items")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestBody))
                .andExpect(status().isForbidden());
        assertEquals(0, countRowsInTableWhere("menuitems", "title = 'New' and price = 1 and category = 1"));
    }

    @Test
    public void testCreateMenuItemWithInvalidTitle() throws Exception {
        String requestBody = "{\"title\": \" \", \"price\": 1}";
        mockMvc.perform(post("/menu/categories/1/items")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestBody)
                        .session(authenticate()))
                .andExpect(status().isBadRequest());
        assertEquals(0, countRowsInTableWhere("menuitems", "title = ' ' and price = 1 and category = 1"));
    }

    @Test
    public void testCreateMenuItemWithNotUniqueTitle() throws Exception {
        String notUniqueTitle = menuService.getMenuItem(1L).getTitle();
        String requestBody = "{\"title\": \"" + notUniqueTitle + "\", \"price\": 1}";
        mockMvc.perform(post("/menu/categories/1/items")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestBody)
                        .session(authenticate()))
                .andExpect(status().isBadRequest());
        assertEquals(0, countRowsInTableWhere("menuitems", "title = '" + notUniqueTitle + "' and price = 1 and category = 1"));
    }

    @Test
    public void testCreateMenuItemWithInvalidPrice() throws Exception {
        String requestBody = "{\"title\": \"New\", \"price\": 0}";
        mockMvc.perform(post("/menu/categories/1/items")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestBody)
                        .session(authenticate()))
                .andExpect(status().isBadRequest());
        assertEquals(0, countRowsInTableWhere("menuitems", "title = 'New' and price = 0 and category = 1"));
    }

    @Test
    public void testEditMenuItem() throws Exception {
        String requestBody = "{\"title\": \"Updated\", \"price\": 1, \"category\": {\"id\": 2}}";
        MenuItem menuItemBefore = menuService.getMenuItem(1L);
        assertNotEquals("Updated", menuItemBefore.getTitle());
        assertNotEquals(1, menuItemBefore.getPrice().longValue());
        assertNotEquals(2, menuItemBefore.getCategory().getId().longValue());
        mockMvc.perform(put("/menu/items/1")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestBody)
                        .session(authenticate()))
                .andExpect(status().isOk());
        MenuItem menuItemAfter = menuService.getMenuItem(1L);
        assertEquals("Updated", menuItemAfter.getTitle());
        assertEquals(1, menuItemAfter.getPrice().longValue());
        assertEquals(2, menuItemAfter.getCategory().getId().longValue());
    }

    @Test
    public void testEditMenuItemWithNotExistingId() throws Exception {
        String requestBody = "{\"title\": \"Updated\", \"price\": 1, \"category\": {\"id\": 2}}";
        mockMvc.perform(put("/menu/items/0")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestBody)
                        .session(authenticate()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteMenuItem() throws Exception {
        assertEquals(1, countRowsInTableWhere("menuitems", "id = 1"));
        mockMvc.perform(delete("/menu/items/1").session(authenticate())).andExpect(status().isOk());
        entityManager.flush();
        assertEquals(0, countRowsInTableWhere("menuitems", "id = 1"));
    }

    private MockHttpSession authenticate() throws Exception {
        return (MockHttpSession) mockMvc.perform(formLogin().user("admin").password("admin"))
                .andExpect(authenticated())
                .andReturn().getRequest().getSession();
    }

}
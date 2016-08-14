package com.cpcs.restaurant.repository;

import com.cpcs.restaurant.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    MenuItem findByTitle(String title);

}
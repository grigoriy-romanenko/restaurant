package com.cpcs.restaurant.repository;

import com.cpcs.restaurant.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c, User u where c.user.id = u.id and u.username = :username")
    Cart findByUsername(@Param("username") String username);

}
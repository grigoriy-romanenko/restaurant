package com.cpcs.restaurant.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cart_menuitem",
            joinColumns = @JoinColumn(name = "cart", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "menuitem", referencedColumnName = "id"))
    private List<MenuItem> menuItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Long getOverallPrice() {
        return menuItems.stream().mapToLong(MenuItem::getPrice).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        if (!id.equals(cart.id)) return false;
        if (!user.equals(cart.user)) return false;
        return menuItems.equals(cart.menuItems);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + menuItems.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", menuItems=" + menuItems + "}";
    }

}
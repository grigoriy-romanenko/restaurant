package com.cpcs.restaurant.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    @NotNull
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "carts_menuitems",
            joinColumns = @JoinColumn(name = "cart", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "menuitem", referencedColumnName = "id"))
    @NotNull
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
        if (getId() != null ? !getId().equals(cart.getId()) : cart.getId() != null) return false;
        if (getUser() != null ? !getUser().equals(cart.getUser()) : cart.getUser() != null) return false;
        return getMenuItems() != null ? getMenuItems().equals(cart.getMenuItems()) : cart.getMenuItems() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getMenuItems() != null ? getMenuItems().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Cart{id=%s, user=%s, menuItems=%s}", id, user, menuItems);
    }

}
package com.cpcs.restaurant.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "orderitems")
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "[order]")
    @NotNull
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuitem")
    private MenuItem menuItem;

    @Column
    @Min(1)
    @NotNull
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        if (id != null ? !id.equals(orderItem.id) : orderItem.id != null) return false;
        if (order != null ? !order.equals(orderItem.order) : orderItem.order != null) return false;
        if (menuItem != null ? !menuItem.equals(orderItem.menuItem) : orderItem.menuItem != null) return false;
        return price != null ? price.equals(orderItem.price) : orderItem.price == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (menuItem != null ? menuItem.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("OrderItem{id=%s, order=%s, menuItem=%s, price=%s}",
                id, order, menuItem, price);
    }

}

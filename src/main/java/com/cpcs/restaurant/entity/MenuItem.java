package com.cpcs.restaurant.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "menuitems")
public class MenuItem implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private Category category;

    @Column
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        if (!id.equals(menuItem.id)) return false;
        if (!title.equals(menuItem.title)) return false;
        if (!category.equals(menuItem.category)) return false;
        return price.equals(menuItem.price);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

}
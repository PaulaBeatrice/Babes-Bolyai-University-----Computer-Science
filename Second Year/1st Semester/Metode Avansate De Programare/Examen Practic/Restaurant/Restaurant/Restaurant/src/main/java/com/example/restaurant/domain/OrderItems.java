package com.example.restaurant.domain;


import java.util.Objects;

public class OrderItems{
    private MenuItem menuItem;
    private Integer quantity;

    public OrderItems( MenuItem menuItem, Integer quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderItems that = (OrderItems) o;
        return menuItem.equals(that.menuItem) && quantity.equals(that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), menuItem, quantity);
    }
}

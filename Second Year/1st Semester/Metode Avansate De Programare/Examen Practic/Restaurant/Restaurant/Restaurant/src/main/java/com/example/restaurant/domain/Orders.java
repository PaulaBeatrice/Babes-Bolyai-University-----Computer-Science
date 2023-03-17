package com.example.restaurant.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Orders extends Entity<Integer>{
    private Table table;
    private LocalDateTime date;
    private List<OrderItems> orderItems;
    OrderStatus orderStatus;

    public Orders(Integer integer, Table table, LocalDateTime date, List<OrderItems> orderItems, OrderStatus orderStatus) {
        super(integer);
        this.table = table;
        this.date = date;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getTableId() {
        return table.getId();
    }
}

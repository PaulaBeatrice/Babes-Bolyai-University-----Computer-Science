package com.example.restaurant.utils.utils;

import com.example.restaurant.domain.Orders;

public class OrderEvent implements Event {
    private ChangeEventType type;
    private Orders order;

    public OrderEvent(ChangeEventType type, Orders order) {
        this.type = type;
        this.order = order;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Orders getOrder() {
        return order;
    }
}

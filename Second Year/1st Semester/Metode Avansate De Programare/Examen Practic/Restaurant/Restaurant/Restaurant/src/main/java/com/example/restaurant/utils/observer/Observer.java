package com.example.restaurant.utils.observer;

import com.example.restaurant.utils.utils.Event;

public interface Observer <E extends Event> {
    void update(E event);
}

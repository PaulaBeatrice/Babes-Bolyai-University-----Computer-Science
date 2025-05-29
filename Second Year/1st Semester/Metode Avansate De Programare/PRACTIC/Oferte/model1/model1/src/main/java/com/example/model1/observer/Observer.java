package com.example.model1.observer;

import com.example.model1.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}

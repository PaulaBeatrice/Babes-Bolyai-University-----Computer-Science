package com.example.l6.socialnetwork.observer;

import com.example.l6.socialnetwork.event.Event;

public interface Observer<E extends Event> {
    void update(E e);
}


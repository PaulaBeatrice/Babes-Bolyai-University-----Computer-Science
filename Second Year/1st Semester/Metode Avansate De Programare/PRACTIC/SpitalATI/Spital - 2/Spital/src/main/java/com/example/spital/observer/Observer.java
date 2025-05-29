package com.example.spital.observer;


import com.example.spital.event.Event;

public interface Observer <E extends Event> {
    void update(E event);
}

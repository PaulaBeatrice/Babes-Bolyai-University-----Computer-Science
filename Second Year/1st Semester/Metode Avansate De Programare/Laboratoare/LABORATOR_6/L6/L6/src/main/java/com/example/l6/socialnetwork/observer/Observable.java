package com.example.l6.socialnetwork.observer;

import com.example.l6.socialnetwork.event.Event;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> e);
    void removeObserver(Observer<E> e);
    void notifyObservers(E t);
}

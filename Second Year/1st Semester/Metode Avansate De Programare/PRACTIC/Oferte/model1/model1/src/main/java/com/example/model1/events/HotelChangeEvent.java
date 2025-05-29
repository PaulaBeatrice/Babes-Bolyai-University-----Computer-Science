package com.example.model1.events;

import com.example.model1.domains.HotelWithDate;

public class HotelChangeEvent implements Event {
    private final ChangeEventType type;
    private final HotelWithDate data;
    private HotelWithDate oldData;

    public HotelChangeEvent(ChangeEventType type, HotelWithDate data) {
        this.type = type;
        this.data = data;
    }

    public HotelChangeEvent(ChangeEventType type, HotelWithDate data, HotelWithDate oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public HotelWithDate getData() {
        return data;
    }

    public HotelWithDate getOldData() {
        return oldData;
    }
}

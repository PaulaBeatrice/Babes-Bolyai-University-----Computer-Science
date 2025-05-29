package com.example.spital.event;

import com.example.spital.domain.Pat;

public class PatEvent implements Event {
    private ChangeEventType type;
    private Pat pat;

    public PatEvent(ChangeEventType type, Pat pat) {
        this.type = type;
        this.pat = pat;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Pat getPat() {
        return pat;
    }
}


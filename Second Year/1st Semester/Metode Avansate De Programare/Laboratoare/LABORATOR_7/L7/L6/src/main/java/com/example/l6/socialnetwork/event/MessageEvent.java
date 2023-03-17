package com.example.l6.socialnetwork.event;

import com.example.l6.socialnetwork.Domain.Message;

public class MessageEvent implements Event {

    private ChangeEventType type;
    private Message message, oldMessage;

    public MessageEvent(ChangeEventType type, Message message) {
        this.type = type;
        this.message = message;
    }
    public MessageEvent(ChangeEventType type, Message message, Message oldMessage) {
        this.type = type;
        this.message = message;
        this.oldMessage = oldMessage;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Message getMessage() {
        return message;
    }

    public Message getOldMessage() {
        return oldMessage;
    }

}


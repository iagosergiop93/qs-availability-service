package com.booking.qs_availability_service.dtos;

import lombok.Data;

@Data
public class Message {
    private String code;
    private String description;
    private MessageType errorType = MessageType.ERROR;

    public Message(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Message(String code, String description, MessageType errorType) {
        this.code = code;
        this.description = description;
        this.errorType = errorType;
    }
}

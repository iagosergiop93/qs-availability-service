package com.booking.qs_availability_service.dtos;

import lombok.Data;

@Data
public class Message {
    private String code;
    private String description;

    public Message(String code, String description) {
        this.code = code;
        this.description = description;
    }
}

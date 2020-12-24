package com.example.application.views.main;

import java.time.LocalDateTime;

public class ChatMessage {
    private String from;
    private LocalDateTime time;
    private String message;

    public ChatMessage(String from, LocalDateTime time, String message) {
        this.from = from;
        this.time = time;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }


    public LocalDateTime getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }
}

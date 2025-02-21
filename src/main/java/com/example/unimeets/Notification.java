package com.example.unimeets;
import java.util.Date;

public class Notification {
    private String message;
    private Date timestamp;
    private MyAppUser recipient;
    private boolean read;

    // Constructor
    public Notification(String message, MyAppUser recipient) {
        this.message = message;
        this.timestamp = new Date(); // sets the current date and time
        this.recipient = recipient;
        this.read = false; // notifications are unread by default
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public MyAppUser getRecipient() {
        return recipient;
    }

    public void setRecipient(MyAppUser recipient) {
        this.recipient = recipient;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "Notification{message='" + message + "', timestamp=" + timestamp + ", recipient=" + recipient.getName() + ", read=" + read + "}";
    }
}


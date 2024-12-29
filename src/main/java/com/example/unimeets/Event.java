package com.example.unimeets;
public class Event {
    private String name;

    // Constructor
    public Event(String name) {
        this.name = name;
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "You will attend the event:" + name;
    }
}



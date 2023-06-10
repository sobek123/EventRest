package com.example.demo.model;

import org.springframework.hateoas.RepresentationModel;

import java.util.*;


public class Event  extends RepresentationModel<Event> {
    private Long id;
    private String name;
    private String type;
    private String details;
    private Date eventDate;

    public Event() {}

    public Event(Long id, String name, String type, String details, Date eventDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.details = details;
        this.eventDate = eventDate;
    }

    public Long getId () { return id; }
    public void setId (Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", details='" + details + '\'' +
                ", eventDate=" + eventDate +
                '}';
    }
}

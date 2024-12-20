package com.example.Travel_agency.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class event {
    private String event_name;
    private String event_date;
    private String location;
    private Double price;
    private String category;

    public event() {
    }

    @JsonCreator
    public event(
            @JsonProperty("event_name") String event_name,
            @JsonProperty("event_date") String event_date,
            @JsonProperty("location") String location,
            @JsonProperty("price") Double price,
            @JsonProperty("category") String category) {
        this.event_name = event_name;
        this.event_date = event_date;
        this.location = location;
        this.price = price;
        this.category = category;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

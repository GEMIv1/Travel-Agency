package com.example.Travel_agency.interfaces.event_related_interfaces;

import java.util.List;

import com.example.Travel_agency.entities.event;

public interface ISearchEventService {
    public List<event> searchEvents(String eventName, String eventDate, String location, Double price, String category, List<event> events);
}

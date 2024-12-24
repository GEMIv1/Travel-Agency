package com.example.Travel_agency.services.event_services;

import com.example.Travel_agency.entities.event;
import com.example.Travel_agency.interfaces.IService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("searchEventService")
public class searchEventService implements IService {

    public List<event> searchEvents(String eventName, String eventDate, String location, Double price, String category, List<event> events) {
        List<event> filteredEvents = new ArrayList<>();
    
        for (event evt : events) {
            boolean matches = true;
    
            if (eventName != null && !eventName.trim().isEmpty() && !evt.getEvent_name().trim().equalsIgnoreCase(eventName.trim())) {
                matches = false;
            }
            if (eventDate != null && !eventDate.trim().isEmpty() &&
                    !evt.getEvent_date().trim().equals(eventDate.trim())) {
                matches = false;
            }
            if (location != null && !location.trim().isEmpty() &&
                    !evt.getLocation().trim().equalsIgnoreCase(location.trim())) {
                matches = false;
            }
            if (price != null && !evt.getPrice().equals(price)) {
                matches = false;
            }
            if (category != null && !category.trim().isEmpty() &&
                    !evt.getCategory().trim().equalsIgnoreCase(category.trim())) {
                matches = false;
            }
    
            if (matches) {
                filteredEvents.add(evt);
            }
        }
    
        return filteredEvents;
    }

    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if (!"searchEvent".equals(operationType)) {
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }

        String eventName = (String) params.get("eventName");
        String eventDate = (String) params.get("eventDate");
        String location = (String) params.get("location");
        Double price = (Double) params.get("price");
        String category = (String) params.get("category");
        List<event> events = (List<event>) params.get("events");

        return searchEvents(eventName, eventDate, location, price, category, events);
    }
}
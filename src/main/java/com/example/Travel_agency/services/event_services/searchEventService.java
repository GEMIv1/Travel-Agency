package com.example.Travel_agency.services.event_services;

import com.example.Travel_agency.entities.event;
import com.example.Travel_agency.interfaces.event_related_interfaces.ISearchEventService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class searchEventService implements ISearchEventService {

    @Override
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
}
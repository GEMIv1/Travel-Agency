package com.example.Travel_agency.services;

import com.example.Travel_agency.entities.events;
import com.example.Travel_agency.stored_data.eventDatabase;
import com.example.Travel_agency.stored_data.bookingEventDataBase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventsService {

    private final eventDatabase eventdatabase;
    private final bookingEventDataBase bookingEventDatabase;

    public EventsService(eventDatabase eventdatabase, bookingEventDataBase bookingEventDatabase) {
        this.eventdatabase = eventdatabase;
        this.bookingEventDatabase = bookingEventDatabase;
    }

    public List<events> searchEvents(String eventName, String eventDate, String location, String price, String category) {
        List<events> events = eventdatabase.getEvents(null, null, null, null, null); // Get all events from eventDatabase
        return events.stream()
                .filter(event -> eventName == null || event.getEvent_name().equalsIgnoreCase(eventName))
                .filter(event -> eventDate == null || event.getEvent_date().equals(eventDate))
                .filter(event -> location == null || event.getLocation().equalsIgnoreCase(location))
                .filter(event -> category == null || event.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public events bookEvent(String eventName, String eventDate, String location, double price, String category) {
        events newEvent = new events(eventName, eventDate, location, price, category);
        bookingEventDatabase.saveEvent(newEvent); // Save event to bookingEventDatabase
        return newEvent;
    }
}

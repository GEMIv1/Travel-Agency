package com.example.Travel_agency.controllers;

import com.example.Travel_agency.entities.events;
import com.example.Travel_agency.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/travel_agency/events")  // This defines the base URL path for all endpoints in this controller
public class EventController {

    @Autowired
    private EventsService eventsService;

    // Removed '/travel_agency/' from the method path to avoid redundancy
    @GetMapping("/search_events")  // This is the actual endpoint: /travel_agency/events/search_events
    public List<events> searchEvents(
            @RequestParam(required = false) String eventName,
            @RequestParam(required = false) String eventDate,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String price,
            @RequestParam(required = false) String category) {

        return eventsService.searchEvents(eventName, eventDate, location, price, category);
    }

    @PostMapping("/book")
    public events bookEvent(
            @RequestParam String eventName,
            @RequestParam String eventDate,
            @RequestParam String location,
            @RequestParam String price,
            @RequestParam String category) {

        return eventsService.bookEvent(eventName, eventDate, location, Double.parseDouble(price), category);
    }
}

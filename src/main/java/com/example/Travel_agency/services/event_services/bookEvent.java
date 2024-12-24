package com.example.Travel_agency.services.event_services;


import com.example.Travel_agency.entities.event;
import com.example.Travel_agency.interfaces.IService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("bookEvent")
public class bookEvent implements IService{

    public event bookingEventService(String eventName, List<event> events) {

        for(event e: events){
            if(e.getEvent_name().trim().equalsIgnoreCase(eventName.trim())){
                return e;
            }
        }
        return null;
    }

    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if (!"bookEvent".equals(operationType)) {
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }

        String eventName = (String) params.get("eventName");
        List<event> events = (List<event>) params.get("events");

        return bookingEventService(eventName, events);
    }

    
    
}

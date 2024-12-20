package com.example.Travel_agency.services.event_services;


import com.example.Travel_agency.entities.event;
import com.example.Travel_agency.interfaces.event_related_interfaces.IBookingEventService;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("eventRepository")
public class bookEvent implements IBookingEventService{

    @Override
    public event bookingEventService(String eventName, List<event> events) {

        for(event e: events){
            if(e.getEvent_name().trim().equalsIgnoreCase(eventName.trim())){
                return e;
            }
        }
        return null;
    }

    
    
}

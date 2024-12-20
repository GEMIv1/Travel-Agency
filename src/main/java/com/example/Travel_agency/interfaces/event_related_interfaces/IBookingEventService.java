package com.example.Travel_agency.interfaces.event_related_interfaces;

import java.util.List;

import com.example.Travel_agency.entities.event;

public interface IBookingEventService {


    public event bookingEventService(String eventName,  List<event> events);
}

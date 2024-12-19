package com.example.Travel_agency.interfaces;

import com.example.Travel_agency.entities.events;

import java.util.List;

public interface IEventRepository {

    public List<events> getEvents(String event_name, String event_date , String location, String price, String categorey);
    public void saveEvent(events events);
}

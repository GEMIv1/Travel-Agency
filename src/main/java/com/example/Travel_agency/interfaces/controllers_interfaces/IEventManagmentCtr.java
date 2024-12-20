package com.example.Travel_agency.interfaces.controllers_interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.event;

@Service
public interface IEventManagmentCtr {
    public void bookEvent(
             String token,
             String eventName);

    public List<event> searchEvents(
             String event_name,
             String event_date,
             String location,
             Double price,
             String category);
}

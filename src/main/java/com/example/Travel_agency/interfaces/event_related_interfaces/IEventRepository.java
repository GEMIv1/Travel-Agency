package com.example.Travel_agency.interfaces.event_related_interfaces;

import com.example.Travel_agency.entities.event;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository {

    public List<event> getEvents();
}

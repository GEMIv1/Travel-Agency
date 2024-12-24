package com.example.Travel_agency.controllers;

import com.example.Travel_agency.entities.bookingEvent;
import com.example.Travel_agency.entities.event;
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IBookingRepository;
import com.example.Travel_agency.interfaces.IExternalRepository;
import com.example.Travel_agency.interfaces.IMessageRepository;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;
import com.example.Travel_agency.interfaces.controllers_interfaces.IEventManagmentCtr;
import com.example.Travel_agency.services.hotel_services.bookHotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class eventManagmentCtr implements IEventManagmentCtr{

    @Autowired
    @Qualifier("searchEventService")
    private IService eventSearch;
    @Autowired
    @Qualifier("eventDatabase")
    private IExternalRepository<event> eventRepository;
    @Autowired
    @Qualifier("bookEvent")
    private IService bookingEventService;
    @Autowired
    private IUserAuthRepository authRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    @Qualifier("bookingEventDataBase")
    private IBookingRepository<bookingEvent> bookingRepository;
    @Autowired
    private IMessageRepository messageRepository;


    public List<event> searchEvents(
             String event_name,
             String event_date,
             String location,
             Double price,
             String category) {
            
            Map<String, Object> params = new HashMap<>();
            params.put("eventName", event_name);
            params.put("eventDate", event_date);
            params.put("location", location);
            params.put("price", price);
            params.put("category", category);
            params.put("events", eventRepository.getAll());



        return (List<event>)eventSearch.performOperation("searchEvent", params);
    }

    public void bookEvent(
             String token,
             String eventName) {


        user usr  = authRepository.getUserWithToken(token, userRepository.getAllUsers());

        Map<String, Object> paramsBookingEventService = new HashMap<>();
        paramsBookingEventService.put("eventName", eventName);
        paramsBookingEventService.put("events", eventRepository.getAll());

        event evt = (event)bookingEventService.performOperation("bookEvent", paramsBookingEventService);
        if(evt != null){
            bookingRepository.save(new bookingEvent(usr.getUserName(), evt));
            messageRepository.saveMessage(new message(usr.getChannel(),"Dear "+usr.getUserName()+": your booking for " +eventName+" is done successfully ","NOT SENT" ));

        }
    }
}

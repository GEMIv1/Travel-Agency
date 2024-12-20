package com.example.Travel_agency.controllers;

import com.example.Travel_agency.entities.bookingEvent;
import com.example.Travel_agency.entities.event;
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.controllers_interfaces.IEventManagmentCtr;
import com.example.Travel_agency.interfaces.event_related_interfaces.IBookingEventRepository;
import com.example.Travel_agency.interfaces.event_related_interfaces.IBookingEventService;
import com.example.Travel_agency.interfaces.event_related_interfaces.IEventRepository;
import com.example.Travel_agency.interfaces.event_related_interfaces.ISearchEventService;
import com.example.Travel_agency.interfaces.message_related_interfaces.IMessageRepository;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class eventManagmentCtr implements IEventManagmentCtr{

    @Autowired
    private ISearchEventService eventSearch;
    @Autowired
    private IEventRepository eventRepository;
    @Autowired
    private IBookingEventService bookingEventService;
    @Autowired
    private IUserAuthRepository authRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IBookingEventRepository bookingRepository;
    @Autowired
    private IMessageRepository messageRepository;


    public List<event> searchEvents(
             String event_name,
             String event_date,
             String location,
             Double price,
             String category) {


        return eventSearch.searchEvents(event_name, event_date, location, price, category, eventRepository.getEvents());
    }

    public void bookEvent(
             String token,
             String eventName) {


        user usr  = authRepository.getUserWithToken(token, userRepository.getAllUsers());
        event evt = bookingEventService.bookingEventService(eventName, eventRepository.getEvents());
        if(evt != null){
            bookingRepository.saveBooking(new bookingEvent(usr.getUserName(), evt));
            messageRepository.saveMessage(new message(usr.getChannel(),"Dear "+usr.getUserName()+": your booking for " +eventName+" is done successfully ","NOT SENT" ));

        }
    }
}

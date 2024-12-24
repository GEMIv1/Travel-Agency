package com.example.Travel_agency.controllers;


import com.example.Travel_agency.entities.bookingHotel;
import com.example.Travel_agency.entities.event;
import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IBookingRepository;
import com.example.Travel_agency.interfaces.IExternalRepository;
import com.example.Travel_agency.interfaces.IMessageRepository;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;
import com.example.Travel_agency.interfaces.controllers_interfaces.IHotelManagmentCtr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class hotelManagmentCtr implements IHotelManagmentCtr {


    @Autowired
    @Qualifier("hotelDatabase")
    private IExternalRepository<hotel> hotelRepository;
    @Autowired
    @Qualifier("searchHotelService")
    private IService search;
    @Autowired
    @Qualifier("bookingHotelDatabase")
    private IBookingRepository<bookingHotel> bookingRepository;
    @Autowired
    @Qualifier("bookHotel")
    private IService bookingService;
    @Autowired
    private IUserAuthRepository authRepository;
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    @Qualifier("recommendation")
    private IService recommendation;
    @Autowired
    @Qualifier("eventDatabase")
    private IExternalRepository <event> eventRepository;

    



    public List<hotel> searchHotels(
             String location,
             String hotel_name,
             Double minPrice,
             Double maxPrice,
             String roomType) { 
                
                
                System.out.println("Location: " + location);
                System.out.println("Hotel Name: " + hotel_name);
                System.out.println("Min Price: " + minPrice);
                System.out.println("Max Price: " + maxPrice);
                System.out.println("Room Type: " + roomType);

                
        List<hotel> res = hotelRepository.getAll();

        if (res == null || res.isEmpty()) {
            System.out.println("No hotels found.");
            return new ArrayList<>();
        }
        Map<String, Object> paramsSearch = new HashMap<>();
        paramsSearch.put("location", location);
        paramsSearch.put("hotel_name", hotel_name);
        paramsSearch.put("minPrice", minPrice);
        paramsSearch.put("maxPrice", maxPrice);
        paramsSearch.put("roomType", roomType);
        paramsSearch.put("hotels", res);

        
        return (List<hotel>) search.performOperation("searchHotels", paramsSearch);
    }

    public boolean bookHotel(
             String location,
             String token,
             String hotelName,
             String roomType,
             LocalDate startDate,
             LocalDate  endDate) {
        


        user usr = authRepository.getUserWithToken(token, userRepository.getAllUsers());
        
        
        if(usr != null){
            System.out.println(usr.getUserName());
            Map<String, Object> paramsBooking = new HashMap<>();
            paramsBooking.put("location", location);
            paramsBooking.put("hotelName", hotelName);
            paramsBooking.put("roomType", roomType);
            paramsBooking.put("startDate", startDate);
            paramsBooking.put("endDate", endDate);
            paramsBooking.put("bookings", bookingRepository.getAll());
            paramsBooking.put("hotels", hotelRepository.getAll());
            
            double payment = (double)bookingService.performOperation("bookHotel", paramsBooking);
            System.out.println(payment);
            if(payment!=0)
            {   
                messageRepository.saveMessage(new message(usr.getChannel(), "Dear: " + usr.getUserName() + " your booking of the  "+  hotelName + ", room type: " + roomType +" is confirmed ", "NOTSENT"));
                bookingRepository.save(new bookingHotel(hotelName,roomType,usr.getUserName(),startDate,endDate,payment,location));

                Map<String, Object> paramsRecommendation = new HashMap<>();
                paramsRecommendation.put("bookings", bookingRepository.getAll());
                paramsRecommendation.put("events", eventRepository.getAll());


                String message = (String) recommendation.performOperation("recommend", paramsRecommendation);
                if(message.equals("No recommendations available") ) {
                    System.out.println("No recommendations available");
                } else {
                    messageRepository.saveMessage(new message(usr.getChannel(), "Dear: "+ usr.getUserName() + message , "NOTSENT"));;
                }
                
                return true;
            }
            return false;
        }
        
       return false;
    }
}

package com.example.Travel_agency.controllers;


import com.example.Travel_agency.entities.bookingHotel;
import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.event_related_interfaces.IEventRepository;
import com.example.Travel_agency.interfaces.hotel_related_interfaces.IBookingHotelRepository;
import com.example.Travel_agency.interfaces.hotel_related_interfaces.IBookingHotelService;
import com.example.Travel_agency.interfaces.hotel_related_interfaces.IHotelRepository;
import com.example.Travel_agency.interfaces.hotel_related_interfaces.ISearchHotelService;
import com.example.Travel_agency.interfaces.message_related_interfaces.IMessageRepository;
import com.example.Travel_agency.interfaces.recommendation_related_interfaces.IRecommendationService;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.IUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class hotelManagmentCtr {


    @Autowired
    private IHotelRepository hotelRepository;
    @Autowired
    private ISearchHotelService search;
    @Autowired
    private IBookingHotelRepository bookingRepository;
    @Autowired
    private IBookingHotelService bookingService;
    @Autowired
    private IUserAuthRepository authRepository;
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRecommendationService recommendation;
    @Autowired
    private IEventRepository eventRepository;

    



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

                
        List<hotel> res = hotelRepository.getHotels();

        if (res == null || res.isEmpty()) {
            System.out.println("No hotels found.");
            return new ArrayList<>();
        }
        
        return search.searchHotels(location, hotel_name,res,roomType, minPrice, maxPrice);
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
            double payment = bookingService.bookhotel(hotelName,roomType,startDate,endDate,bookingRepository.getAllBookings(),hotelRepository.getHotels(),location);
            System.out.println(payment);
            if(payment!=0)
            {   
                messageRepository.saveMessage(new message(usr.getChannel(), "Dear: " + usr.getUserName() + " your booking of the  "+  hotelName + ", room type: " + roomType +" is confirmed ", "NOTSENT"));
                bookingRepository.saveBooking(new bookingHotel(hotelName,roomType,usr.getUserName(),startDate,endDate,payment,location));
                String message = recommendation.recommend(bookingRepository.getAllBookings(),eventRepository.getEvents());
                if(message.equals("No recommendations available") ) {
                    System.out.println("No recommendations available");
                } else {
                    messageRepository.saveMessage(new message(usr.getChannel(), "Dear: "+ usr.getUserName() + message , "NOT SENT"));;
                }
                
                return true;
            }
            return false;
        }
        
       return false;
    }
}

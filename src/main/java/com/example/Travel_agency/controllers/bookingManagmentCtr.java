package com.example.Travel_agency.controllers;

import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IBookingService;
import com.example.Travel_agency.interfaces.IBookingRepository;
import com.example.Travel_agency.interfaces.IHotelRepository;
import com.example.Travel_agency.interfaces.IMessageRepository;
import com.example.Travel_agency.interfaces.ISearch;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class bookingManagmentCtr {


    @Autowired
    private IHotelRepository hotelRepository;
    @Autowired
    private ISearch search;
    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private IBookingService bookingService;
    @Autowired
    private IUserAuthRepository authRepository;
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    private IUserRepository userRepository;

    



    @GetMapping("/travel_agency/search_hotels")
    public List<hotel> searchHotels(
            @RequestParam String location,
            @RequestParam(required = false) String hotel_name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String roomType) {

                System.out.println("Location: " + location);
                System.out.println("Hotel Name: " + hotel_name);
                System.out.println("Min Price: " + minPrice);
                System.out.println("Max Price: " + maxPrice);
                System.out.println("Room Type: " + roomType);

                
        List<hotel> res = hotelRepository.getHotels(hotel_name, minPrice, maxPrice, roomType, location);

        if (res == null || res.isEmpty()) {
            System.out.println("No hotels found.");
            return new ArrayList<>();
        }
        
        return search.searchHotels(location, hotel_name,res,roomType, minPrice, maxPrice);
    }

    @PostMapping("/travel_agency/book_hotel")
    public boolean bookHotel(
            @RequestParam String location,
            @RequestParam String token,
            @RequestParam String hotelName,
            @RequestParam String roomType,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        


        user usr = authRepository.getUserWithToken(token, userRepository.getAllUsers());
        
        
        if(usr != null){
            System.out.println(usr.getUserName());
            double payment = bookingService.bookhotel(hotelName,roomType,startDate,endDate,bookingRepository.getAllBookings(),hotelRepository.getHotels(hotelName, null, null, roomType,location),location);
            System.out.println(payment);
            if(payment!=0)
            {
                messageRepository.saveMessage(new message(usr.getChannel(), "Dear: " + usr.getUserName() + " your booking of the  "+  hotelName + ", room type: " + roomType +" is confirmed ", "NOTSENT"));
                bookingRepository.saveBooking(hotelName, roomType, usr.getUserName(), startDate, endDate, payment,location);
                return true;
            }
            return false;
        }
        
       return false;
    }
}

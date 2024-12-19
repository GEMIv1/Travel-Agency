package com.example.Travel_agency.controllers;

import com.example.Travel_agency.entities.booking;
import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.entities.room;
import com.example.Travel_agency.entities.userAuth;
import com.example.Travel_agency.interfaces.IBook;
import com.example.Travel_agency.interfaces.IBookingRepository;
import com.example.Travel_agency.interfaces.IHotelRepository;
import com.example.Travel_agency.interfaces.ISearch;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.services.BookingService;
import com.example.Travel_agency.stored_data.authenticationDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private BookingService bookingService = new BookingService();
    @Autowired
    private IUserAuthRepository authRepository;

//    @Autowired
//    private IBook book;
    



    @GetMapping("/travel_agency/search_hotels")
    public List<hotel> searchHotels(
            @RequestParam String hotel_name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String roomType) {

                System.out.println("Hotel Name: " + hotel_name);
                System.out.println("Min Price: " + minPrice);
                System.out.println("Max Price: " + maxPrice);
                System.out.println("Room Type: " + roomType);

                
        List<hotel> res = hotelRepository.getHotels(hotel_name, minPrice, maxPrice, roomType);

        if (res == null || res.isEmpty()) {
            System.out.println("No hotels found.");
            return new ArrayList<>();
        }
        
        return search.searchHotels(hotel_name,res,roomType, minPrice, maxPrice);
    }

    @PostMapping("/travel_agency/book_hotel")
    public boolean bookHotel(
            @RequestParam String token,
            @RequestParam String hotelName,
            @RequestParam String roomType,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        
        boolean isAuthorized = false;
        List<userAuth> userAuths = authRepository.getAllUserAuth();
        String userToBook="";
        for(userAuth u: userAuths){
            if(u.getToken().equals(token)){
                isAuthorized = true;
                userToBook = u.getUsername();
                break;
            }
        }
        if(isAuthorized){
            System.out.println(userToBook);
            double payment = bookingService.bookhotel(hotelName,roomType,startDate,endDate,bookingRepository.getAllBookings(),hotelRepository.getHotels(hotelName, null, null, roomType));
            System.out.println(payment);
            if(payment!=0)
            {
                
                bookingRepository.saveBooking(hotelName, roomType, userToBook, startDate, endDate, payment);
                return true;
            }
            return false;
        }
        
       return false;
    }
}

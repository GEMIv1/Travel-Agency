package com.example.Travel_agency.controllers;

import com.example.Travel_agency.entities.booking;
import com.example.Travel_agency.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class bookingManagmentCtr {

    private BookingService bookingService;

    // Search Hotels endpoint
    @GetMapping("/travel_agency/search_hotels")
    public ResponseEntity<List<booking>> searchHotels(
            @RequestParam String hotel_name,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(required = false) String roomType,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        try {
            List<booking> results = bookingService.searchHotels(hotel_name, minPrice, maxPrice, roomType, startDate, endDate);
            return ResponseEntity.ok(results);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);  // Handle invalid input errors
        }
    }

    // Book Hotel endpoint
    @PostMapping("/travel_agency/book_hotel")
    public ResponseEntity<booking> bookHotel(
            @RequestParam String hotelName,
            @RequestParam String roomType,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        try {
            booking newBooking = bookingService.bookhotel(hotelName, roomType, startDate, endDate);
            return ResponseEntity.ok(newBooking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);  // Handle invalid input errors
        }
    }
}

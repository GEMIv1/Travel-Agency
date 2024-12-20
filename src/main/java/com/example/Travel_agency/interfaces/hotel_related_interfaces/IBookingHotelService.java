package com.example.Travel_agency.interfaces.hotel_related_interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.bookingHotel;
import com.example.Travel_agency.entities.hotel;

@Service
public interface IBookingHotelService {
    public double bookhotel(String hotelName, String roomType, LocalDate startDate, LocalDate endDate, List<bookingHotel> bookings, List<hotel> hotels, String location);
    public double calculatePrice(LocalDate startDate, LocalDate endDate, double price);
}

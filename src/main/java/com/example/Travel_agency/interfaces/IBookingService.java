package com.example.Travel_agency.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.booking;
import com.example.Travel_agency.entities.hotel;

@Service
public interface IBookingService {
    public double bookhotel(String hotelName, String roomType, LocalDate startDate, LocalDate endDate, List<booking> bookings, List<hotel> hotels, String location);
    public double calculatePrice(LocalDate startDate, LocalDate endDate, double price);
}

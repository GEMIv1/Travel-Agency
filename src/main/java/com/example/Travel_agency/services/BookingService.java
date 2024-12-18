package com.example.Travel_agency.services;

import com.example.Travel_agency.entities.booking;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class bookingService {

    private final List<booking> bookings = new ArrayList<>();

    // To search hotels
    /*

    // To book a hotel
    public booking bookhotel(String hotelName, String roomType, LocalDate startDate, LocalDate endDate) {
        try {
            double price = calculatePrice(startDate, endDate);

            booking newBooking = new booking();
            newBooking.setHotel_name(hotelName);
            newBooking.setRoomType(roomType);
            newBooking.setStartDate(startDate);
            newBooking.setEndDate(endDate);
            newBooking.setMinprice(price);
            newBooking.setMaxprice(price);
            bookings.add(newBooking);

            return newBooking;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format.");
        }
    }

    private double calculatePrice(LocalDate startDate, LocalDate endDate) {
        LocalDate start = (startDate);
        LocalDate end = (endDate);
        long days = ChronoUnit.DAYS.between(start, end);
        return days * ratePerDay;
    }*/
}

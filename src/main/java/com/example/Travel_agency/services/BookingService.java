package com.example.Travel_agency.services;

import com.example.Travel_agency.entities.booking;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

    private final List<booking> bookings = new ArrayList<>();

    // To search hotels
    public List<booking> searchHotels(String hotelName, String minPrice, String maxPrice, String roomType, String startDate, String endDate) {
        try {

            double min = minPrice != null ? Double.parseDouble(minPrice) : 0.0;
            double max = maxPrice != null ? Double.parseDouble(maxPrice) : Double.MAX_VALUE;

            return bookings.stream()
                    .filter(booking -> hotelName == null || booking.getHotel_name().equalsIgnoreCase(hotelName))
                    .filter(booking -> booking.getMinprice() >= min)
                    .filter(booking -> booking.getMaxprice() <= max)
                    .filter(booking -> roomType == null || booking.getRoomType().equalsIgnoreCase(roomType))
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price format.");
        }
    }

    // To book a hotel
    public booking bookhotel(String hotelName, String roomType, String startDate, String endDate) {
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

    private double calculatePrice(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        long days = ChronoUnit.DAYS.between(start, end);
        double ratePerDay = 100;  // Fixed rate per day
        return days * ratePerDay;
    }
}

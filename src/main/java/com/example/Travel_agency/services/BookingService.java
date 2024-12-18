package com.example.Travel_agency.services;

import com.example.Travel_agency.entities.booking;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import com.example.Travel_agency.interfaces.IBook;
import org.springframework.stereotype.Service;

public class BookingService  implements IBook {


    // To search hotels


    public boolean bookhotel(String hotelName, String roomType, LocalDate startDate, LocalDate endDate, List<booking> bookings) {

        for (booking booking : bookings) {
            if (!(startDate.isAfter(booking.getEndDate()) || endDate.isBefore(booking.getStartDate())))
            {
                return false;
            }
        }

        return true;

    }

    private double calculatePrice(LocalDate startDate, LocalDate endDate, double price) {
        LocalDate start = (startDate);
        LocalDate end = (endDate);
        long days = ChronoUnit.DAYS.between(start, end);
        return days * price;
    }
}

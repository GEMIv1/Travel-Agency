package com.example.Travel_agency.interfaces;

import com.example.Travel_agency.entities.booking;

import java.time.LocalDate;
import java.util.List;

public interface IBookingRepository {

    public boolean saveBooking(String hotel_name, String roomType, String username, LocalDate startDate, LocalDate endDate, double payAmount);
    public List<booking> getAllBookings();

}

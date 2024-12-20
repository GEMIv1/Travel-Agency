package com.example.Travel_agency.interfaces.hotel_related_interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Travel_agency.entities.bookingHotel;

@Repository
public interface IBookingHotelRepository{

    public boolean saveBooking(bookingHotel e);
    public List<bookingHotel> getAllBookings();

}

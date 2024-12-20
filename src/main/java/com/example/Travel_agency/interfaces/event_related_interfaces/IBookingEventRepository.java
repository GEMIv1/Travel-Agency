package com.example.Travel_agency.interfaces.event_related_interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Travel_agency.entities.bookingEvent;

@Repository
public interface IBookingEventRepository {
        public boolean saveBooking(bookingEvent e);
        public List<bookingEvent> getAllBookings();
}

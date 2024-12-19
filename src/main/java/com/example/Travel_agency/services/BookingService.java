package com.example.Travel_agency.services;

import com.example.Travel_agency.entities.booking;
import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.entities.room;
import com.example.Travel_agency.interfaces.IBookingService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService{


    public double bookhotel(String hotelName, String roomType, LocalDate startDate, LocalDate endDate, List<booking> bookings, List<hotel> hotels, String location) {
        double totalPrice = 0.0;
    
        boolean isValidHotelName = false;
        for (hotel h : hotels) {
            if (h.getName().equals(hotelName)) {
                isValidHotelName = true;
                break;
            }
        }
    
        if (isValidHotelName) {
            boolean isDateAvailable = true;
    
            for (booking booking : bookings) {
                if (hotelName.equals(booking.getHotelName())&&(startDate.isBefore(booking.getEndDate()) && endDate.isAfter(booking.getStartDate()))) {
                    isDateAvailable = false;
                    break;
                }
            }
    
            if (isDateAvailable) {
                boolean foundRoom = false;
                double roomPrice = 0;
    
                for (hotel h : hotels) {
                    for (room r : h.getRooms()) {
                        if (r.getType().equals(roomType)) {
                            roomPrice = r.getPrice();
                            foundRoom = true;
                            break;
                        }
                    }
                    if (foundRoom) break;
                }
    
                if (foundRoom) {
                    totalPrice = calculatePrice(startDate, endDate, roomPrice);
                }
            }
        }
    
        return totalPrice;
    }
    
    public double calculatePrice(LocalDate startDate, LocalDate endDate, double price) {
        LocalDate start = (startDate);
        LocalDate end = (endDate);
        long days = ChronoUnit.DAYS.between(start, end);
        return days * price;
    }


}

package com.example.Travel_agency.services.hotel_services;

import com.example.Travel_agency.entities.bookingHotel;
import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.entities.room;
import com.example.Travel_agency.interfaces.hotel_related_interfaces.IBookingHotelService;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class bookHotel implements IBookingHotelService{


    @Override
    public double bookhotel(String hotelName, String roomType, LocalDate startDate, LocalDate endDate, List<bookingHotel> bookings, List<hotel> hotels, String location) {
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
    
            for (bookingHotel booking : bookings) {
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
    
    @Override
    public double calculatePrice(LocalDate startDate, LocalDate endDate, double price) {
        LocalDate start = (startDate);
        LocalDate end = (endDate);
        long days = ChronoUnit.DAYS.between(start, end);
        return days * price;
    }


}

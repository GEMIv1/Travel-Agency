package com.example.Travel_agency.services.hotel_services;

import com.example.Travel_agency.entities.bookingHotel;
import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.entities.room;
import com.example.Travel_agency.interfaces.IService;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("bookHotel")
public class bookHotel implements IService{


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
    
    public double calculatePrice(LocalDate startDate, LocalDate endDate, double price) {
        LocalDate start = (startDate);
        LocalDate end = (endDate);
        long days = ChronoUnit.DAYS.between(start, end);
        return days * price;
    }

    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {

        if (!"bookHotel".equals(operationType)) {
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }

        String hotelName = (String) params.get("hotelName");
        String roomType = (String) params.get("roomType");
        LocalDate startDate = (LocalDate) params.get("startDate");
        LocalDate endDate = (LocalDate) params.get("endDate");
        List<bookingHotel> bookings = (List<bookingHotel>) params.get("bookings");
        List<hotel> hotels = (List<hotel>) params.get("hotels");
        String location = (String) params.get("location");

        return bookhotel(hotelName, roomType, startDate, endDate, bookings, hotels, location);
    }


}

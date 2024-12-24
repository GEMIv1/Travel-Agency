package com.example.Travel_agency.services.recommendation_services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.example.Travel_agency.entities.bookingHotel;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.entities.event;

@Service("recommendation")
public class recommendation implements IService {

    public String recommend(List<bookingHotel> bookings, List<event> events) {
        Set<String> bookingLocations = new HashSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (bookingHotel booking : bookings) {
            bookingLocations.add(booking.getLocation().trim().toLowerCase());
        }

        for (event event : events) {
            String eventLocation = event.getLocation().trim().toLowerCase();
            LocalDate eventDate = LocalDate.parse(event.getEvent_date(), formatter); 

            for (bookingHotel booking : bookings) {
                LocalDate startDate = booking.getStartDate(); 
                LocalDate endDate = booking.getEndDate();

                if (bookingLocations.contains(eventLocation) &&
                    !eventDate.isBefore(startDate) && 
                    !eventDate.isAfter(endDate)) {
                    
                    return " Recommendation: " + event.getEvent_name() +
                           " is happening near your hotel at " + event.getLocation() +
                           " on " + event.getEvent_date();
                }
            }
        }

        return "No recommendations available";
    }

    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        
        if (!"recommend".equals(operationType)) {
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }

        List<bookingHotel> bookings = (List<bookingHotel>) params.get("bookings");
        List<event> events = (List<event>) params.get("events");

        return recommend(bookings, events);
    }
}

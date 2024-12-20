package com.example.Travel_agency.stored_data;

import com.example.Travel_agency.entities.bookingEvent;
import com.example.Travel_agency.interfaces.event_related_interfaces.IBookingEventRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class bookingEventDataBase implements IBookingEventRepository{

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String path = "D:\\SDA_Project\\bookingEventData.json";
    
    @Override
    public List<bookingEvent> getAllBookings() {
        List<bookingEvent> bookings = new ArrayList<>();
        File file = new File(path);
        if(file.exists()){
            try {
                bookings = objectMapper.readValue(file, new TypeReference<List<bookingEvent>>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return bookings;
    }
    
    @Override
    public boolean saveBooking(bookingEvent event) {
        List<bookingEvent> bookings = getAllBookings();
        bookings.add(event);

        try {
            objectMapper.writeValue(new File(path), bookings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

 
    

   
}

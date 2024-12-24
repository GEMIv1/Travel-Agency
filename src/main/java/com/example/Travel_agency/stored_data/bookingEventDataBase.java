package com.example.Travel_agency.stored_data;

import com.example.Travel_agency.entities.bookingEvent;
import com.example.Travel_agency.interfaces.IBookingRepository;
//import com.example.Travel_agency.interfaces.event_related_interfaces.IBookingEventRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("bookingEventDataBase")
public class bookingEventDataBase implements IBookingRepository<bookingEvent> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String path = "D:\\SDA_Project\\bookingEventData.json";

    @Override
    public boolean save(bookingEvent entity) {
        List<bookingEvent> bookings = getAll();
        bookings.add(entity);
        try {
            objectMapper.writeValue(new File(path), bookings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void update(bookingEvent entity) {
        // TODO: Implement update logic
    }

    @Override
    public List<bookingEvent> getAll() {
        List<bookingEvent> bookings = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            try {
                bookings = objectMapper.readValue(file, new TypeReference<List<bookingEvent>>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return bookings;
    }
}


package com.example.Travel_agency.stored_data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.Travel_agency.entities.bookingHotel;
import com.example.Travel_agency.interfaces.IBookingRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("bookingHotelDatabase")
public class bookingHotelDatabase implements IBookingRepository<bookingHotel> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String path = "D:\\SDA_Project\\bookingHotelData.json";

    @Override
    public boolean save(bookingHotel entity) {
        List<bookingHotel> bookings = getAll();
        bookings.add(entity);
        try {
            objectMapper.writeValue(new File(path), bookings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void update(bookingHotel entity) {
        // TODO: Implement update logic
    }

    @Override
    public List<bookingHotel> getAll() {
        List<bookingHotel> bookings = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            try {
                bookings = objectMapper.readValue(file, new TypeReference<List<bookingHotel>>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return bookings;
    }
}


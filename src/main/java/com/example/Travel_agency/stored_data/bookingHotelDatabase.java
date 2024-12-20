package com.example.Travel_agency.stored_data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.Travel_agency.entities.bookingHotel;
import com.example.Travel_agency.interfaces.hotel_related_interfaces.IBookingHotelRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class bookingHotelDatabase implements IBookingHotelRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String path = "D:\\SDA_Project\\bookingHotelData.json";

    @Override
    public List<bookingHotel> getAllBookings() {
        List<bookingHotel> bookings = new ArrayList<>();
        File file = new File(path);
        if(file.exists()){
            try {
                bookings = objectMapper.readValue(file, new TypeReference<List<bookingHotel>>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return bookings;
    }

    @Override
    public boolean saveBooking(bookingHotel h) {
        List<bookingHotel> bookings = getAllBookings();
        bookings.add(h);

        try {
            objectMapper.writeValue(new File(path), bookings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


  
    

}

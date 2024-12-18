package com.example.Travel_agency.stored_data;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.Travel_agency.entities.booking;
import com.example.Travel_agency.interfaces.IBookingRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class bookingDatabase implements IBookingRepository {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String path = "SDA_Project\\bookingData.json";


    public boolean book(String hotel_name,String roomType, String username, LocalDate startDate, LocalDate endDate, double payAmount){

        List<booking> bookings =getAllBookings();
        bookings.add(new booking(hotel_name, roomType,payAmount, username, startDate, endDate));

        try {
            objectMapper.writeValue(new File(path), bookings);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<booking> getAllBookings() {
        List<booking> bookings = new ArrayList<>();
        File file = new File(path);
        if(file.exists()){
            try {
                bookings = objectMapper.readValue(file, new TypeReference<List<booking>>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return bookings;
    }

}

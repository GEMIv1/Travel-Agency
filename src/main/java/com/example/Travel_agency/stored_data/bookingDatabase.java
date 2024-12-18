package com.example.Travel_agency.stored_data;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.interfaces.IBookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class bookingDatabase implements IBookingRepository{
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String path = "D:\\SDA_Project\\bookingData.json";


    public boolean book(String hotel_name,String roomType, String username, LocalDate startDate, LocalDate endDate, String payAmount){

        return false;
    }

}

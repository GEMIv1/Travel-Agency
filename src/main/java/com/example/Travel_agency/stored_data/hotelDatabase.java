package com.example.Travel_agency.stored_data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.interfaces.hotel_related_interfaces.IHotelRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class hotelDatabase implements IHotelRepository {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final String path = "D:\\SDA_Project\\hotelData.json";

    @Override
    public List<hotel> getHotels() {
        List<hotel> hotels = new ArrayList<>();
        try{
            File file = new File(path);
            if(file.exists()){
                hotels = objectMapper.readValue(file, new TypeReference<ArrayList<hotel>>() {});
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return hotels;
    }

}

package com.example.Travel_agency.interfaces;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.Travel_agency.entities.hotel;

@Repository
public interface IHotelRepository {
    public List<hotel> getHotels(String hotel_name, Double minPrice, Double maxPrice, String roomType,String location);
}
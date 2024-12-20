package com.example.Travel_agency.interfaces.hotel_related_interfaces;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.example.Travel_agency.entities.hotel;

@Repository
public interface IHotelRepository {
    public List<hotel> getHotels();
}
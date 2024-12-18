package com.example.Travel_agency.interfaces;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.hotel;

@Service
public interface ISearch  {

    public List<hotel> searchHotels(
    String hotelName,
    List<hotel> searchHotels,
    String roomType,
    Double minPrice,
    Double maxPrice
);

}

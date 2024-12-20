package com.example.Travel_agency.interfaces.hotel_related_interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.hotel;

@Service
public interface ISearchHotelService  {

    public List<hotel> searchHotels(
    String location,
    String hotelName,
    List<hotel> searchHotels,
    String roomType,
    Double minPrice,
    Double maxPrice
);

}

package com.example.Travel_agency.interfaces.controllers_interfaces;

import java.time.LocalDate;
import java.util.List;
import com.example.Travel_agency.entities.hotel;
import org.springframework.stereotype.Service;

@Service
public interface IHotelManagmentCtr {
    public List<hotel> searchHotels(
             String location,
             String hotel_name,
             Double minPrice,
             Double maxPrice,
             String roomType) ;

        public boolean bookHotel(
             String location,
             String token,
             String hotelName,
             String roomType,
             LocalDate startDate,
             LocalDate  endDate);
}

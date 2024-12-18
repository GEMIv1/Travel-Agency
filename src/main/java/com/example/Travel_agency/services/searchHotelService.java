package com.example.Travel_agency.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.entities.room;
import com.example.Travel_agency.interfaces.IRoomFilter;
import com.example.Travel_agency.interfaces.ISearch;
import com.example.Travel_agency.services.room_filter.RoomPriceFilter;
import com.example.Travel_agency.services.room_filter.RoomTypeFilter;

@Service
public class searchHotelService implements ISearch {

    public List<hotel> searchHotels(
    String hotelName,
    List<hotel> searchHotels,
    String roomType,
    Double minPrice,
    Double maxPrice
) {
     List<hotel> matchingHotels = new ArrayList<>();

     List<hotel> filteredHotels = searchHotels.stream()
        .filter(h -> hotelName == null || h.getName().equalsIgnoreCase(hotelName))
        .collect(Collectors.toList());

    List<IRoomFilter> filters = new ArrayList<>();
    if (roomType != null && !roomType.isEmpty()) {
        filters.add(new RoomTypeFilter(roomType)); 
    }
    if (minPrice != null || maxPrice != null) {
        filters.add(new RoomPriceFilter(minPrice, maxPrice)); 
    }

    for (hotel h : filteredHotels) {
        List<room> filteredRooms = h.getRooms().stream()
            .filter(r -> filters.stream().allMatch(filter -> filter.matches(r)))
            .collect(Collectors.toList());

        if (!filteredRooms.isEmpty()) {
            hotel filteredHotel = new hotel(h.getName(), filteredRooms);
            matchingHotels.add(filteredHotel);
        }
    }

    if (matchingHotels.isEmpty()) {
        System.out.println("No hotels found matching the criteria.");
    }

    return matchingHotels;
    }

    
    

}

package com.example.Travel_agency.services.hotel_services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.entities.room;
import com.example.Travel_agency.interfaces.hotel_related_interfaces.IRoomFilterService;
import com.example.Travel_agency.interfaces.hotel_related_interfaces.ISearchHotelService;
import com.example.Travel_agency.services.room_filter.RoomPriceFilter;
import com.example.Travel_agency.services.room_filter.RoomTypeFilter;

@Service
public class searchHotelService implements ISearchHotelService {

    public List<hotel> searchHotels(
    String location,
    String hotelName,
    List<hotel> searchHotels,
    String roomType,
    Double minPrice,
    Double maxPrice) {

    List<hotel> matchingHotels = new ArrayList<>();
    List<hotel> filteredHotelsLocation = new ArrayList<>();

    if (location != null && !location.trim().isEmpty()) {
        for (hotel h : searchHotels) {
            if (h.getLocation().equalsIgnoreCase(location)) {
                System.out.println("Adding hotel: " + h.getName() + " with location: " + h.getLocation());
                filteredHotelsLocation.add(h);
            }
        }
    } else {
        filteredHotelsLocation = new ArrayList<>(searchHotels);
    }

    List<hotel> filteredHotelsName = new ArrayList<>();
    if(filteredHotelsLocation.isEmpty()) { 
        for (hotel h : searchHotels) {
            if (h.getName().equalsIgnoreCase(hotelName)) {
                System.out.println(h.getLocation());
                filteredHotelsName.add(h);
            }
        }
    } else {
        for (hotel h : filteredHotelsLocation) {
            if (h.getName().equalsIgnoreCase(hotelName)) {
                filteredHotelsName.add(h);
            }
        }
    }

    List<IRoomFilterService> filters = new ArrayList<>();
    if (roomType != null && !roomType.isEmpty()) {
        filters.add(new RoomTypeFilter(roomType)); 
    }
    if (minPrice != null || maxPrice != null) {
        filters.add(new RoomPriceFilter(minPrice, maxPrice)); 
    }

    for (hotel h : filteredHotelsName) {
        List<room> filteredRooms = h.getRooms().stream()
            .filter(r -> filters.stream().allMatch(filter -> filter.matches(r)))
            .collect(Collectors.toList());

        if (!filteredRooms.isEmpty()) {
            hotel filteredHotel = new hotel(h.getName(), filteredRooms, h.getLocation());
            matchingHotels.add(filteredHotel);
        }
    }

    if (matchingHotels.isEmpty()) {
        System.out.println("No hotels found matching the criteria.");
    }

    return matchingHotels.isEmpty() ? filteredHotelsLocation : matchingHotels;
}

    
    

}

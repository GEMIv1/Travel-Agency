package com.example.Travel_agency.services.hotel_services;

import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.entities.room;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.services.room_filter.RoomPriceFilter;
import com.example.Travel_agency.services.room_filter.RoomTypeFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("searchHotelService")
public class searchHotelService implements IService {

    public List<hotel> searchHotels(
            String location,
            String hotelName,
            List<hotel> searchHotels,
            String roomType,
            Double minPrice,
            Double maxPrice) {

        List<hotel> filteredHotels = new ArrayList<>();
        for (hotel h : searchHotels) {// filter by location
            if (location == null || location.trim().isEmpty() || h.getLocation().equalsIgnoreCase(location)) {
                filteredHotels.add(h);
            }
        }

        if (hotelName != null && !hotelName.trim().isEmpty()) {// filter by hotel name
            List<hotel> tempHotels = new ArrayList<>();
            for (hotel h : filteredHotels) {
                if (h.getName().equalsIgnoreCase(hotelName)) {
                    tempHotels.add(h);
                }
            }
            filteredHotels = tempHotels;
        }

        List<IService> roomFilters = new ArrayList<>();
        if (roomType != null && !roomType.isEmpty()) {
            roomFilters.add(new RoomTypeFilter());
        }
        if (minPrice != null || maxPrice != null) {
            roomFilters.add(new RoomPriceFilter());
        }

        List<hotel> resultHotels = new ArrayList<>();
        for (hotel h : filteredHotels) {
            List<room> filteredRooms = h.getRooms();
            for (IService filter : roomFilters) {
                Map<String, Object> params = Map.of(
                        "rooms", filteredRooms,
                        "roomType", roomType != null ? roomType : "",
                        "minPrice", minPrice != null ? minPrice : Double.MIN_VALUE,
                        "maxPrice", maxPrice != null ? maxPrice : Double.MAX_VALUE
                );
                filteredRooms = (List<room>) filter.performOperation(filter instanceof RoomTypeFilter ? "filterByType" : "filterByPrice", params);
            }
            if (!filteredRooms.isEmpty()) {
                resultHotels.add(new hotel(h.getName(), filteredRooms, h.getLocation()));
            }
        }

        return resultHotels;
    }

    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if (!"searchHotels".equalsIgnoreCase(operationType)) {
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }

        String location = (String) params.get("location");
        String hotelName = (String) params.get("hotelName");
        Double minPrice = (Double) params.get("minPrice");
        Double maxPrice = (Double) params.get("maxPrice");
        String roomType = (String) params.get("roomType");
        List<hotel> hotels = (List<hotel>) params.get("hotels");

        return searchHotels(location, hotelName, hotels, roomType, minPrice, maxPrice);
    }
}

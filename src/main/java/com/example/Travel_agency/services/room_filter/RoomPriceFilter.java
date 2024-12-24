package com.example.Travel_agency.services.room_filter;

import com.example.Travel_agency.entities.room;
import com.example.Travel_agency.interfaces.IService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomPriceFilter implements IService {

    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if (!"filterByPrice".equalsIgnoreCase(operationType)) {
            throw new IllegalArgumentException("Invalid operation type for RoomPriceFilter");
        }

        Double minPrice = (Double) params.getOrDefault("minPrice", Double.MIN_VALUE);
        Double maxPrice = (Double) params.getOrDefault("maxPrice", Double.MAX_VALUE);
        List<room> rooms = (List<room>) params.get("rooms");

        if (rooms == null) {
            throw new IllegalArgumentException("The parameter 'rooms' is missing or invalid.");
        }

        return rooms.stream()
                .filter(r -> r.getPrice() >= minPrice && r.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}

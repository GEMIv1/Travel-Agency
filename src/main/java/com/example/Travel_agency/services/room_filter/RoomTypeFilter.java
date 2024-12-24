package com.example.Travel_agency.services.room_filter;

import com.example.Travel_agency.entities.room;
import com.example.Travel_agency.interfaces.IService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoomTypeFilter implements IService {

    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if (!"filterByType".equalsIgnoreCase(operationType) && !"filterByPrice".equalsIgnoreCase(operationType)) {
            throw new UnsupportedOperationException("Unsupported operation type: " + operationType);
        }

        List<room> rooms = (List<room>) params.get("rooms");

        if (rooms == null) {
            throw new IllegalArgumentException("The parameter 'rooms' is missing or invalid.");
        }

        if ("filterByType".equalsIgnoreCase(operationType)) {
            String roomType = (String) params.get("roomType");
            return rooms.stream()
                    .filter(r -> roomType == null || roomType.isEmpty() || r.getType().equalsIgnoreCase(roomType))
                    .collect(Collectors.toList());
        } else if ("filterByPrice".equalsIgnoreCase(operationType)) {
            Double price = (Double) params.get("price");
            return rooms.stream()
                    .filter(r -> price == null || r.getPrice() <= price)
                    .collect(Collectors.toList());
        }

        return rooms;
    }
}

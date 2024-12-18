package com.example.Travel_agency.services.room_filter;


import com.example.Travel_agency.entities.room;
import com.example.Travel_agency.interfaces.IRoomFilter;

public class RoomTypeFilter implements IRoomFilter {

    private String roomType;

    public RoomTypeFilter(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean matches(room r) {
        return roomType == null || roomType.isEmpty() || r.getType().equalsIgnoreCase(roomType);
    }


}

package com.example.Travel_agency.services.room_filter;


import com.example.Travel_agency.entities.room;
import com.example.Travel_agency.interfaces.IRoomFilter;


public class RoomPriceFilter implements IRoomFilter {
    private Double minPrice;
    private Double maxPrice;

    public RoomPriceFilter(Double minPrice, Double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean matches(room r) {
        
        return (minPrice == null || r.getPrice() >= minPrice) &&
               (maxPrice == null || r.getPrice() <= maxPrice);
    }

}

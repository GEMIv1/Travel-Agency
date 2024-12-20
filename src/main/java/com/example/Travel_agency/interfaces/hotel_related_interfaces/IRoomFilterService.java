package com.example.Travel_agency.interfaces.hotel_related_interfaces;

import org.springframework.stereotype.Repository;

import com.example.Travel_agency.entities.room;

@Repository
public interface IRoomFilterService {
     boolean matches(room r);
}

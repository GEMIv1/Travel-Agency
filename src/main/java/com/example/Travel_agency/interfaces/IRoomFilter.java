package com.example.Travel_agency.interfaces;

import org.springframework.stereotype.Repository;

import com.example.Travel_agency.entities.room;

@Repository
public interface IRoomFilter {
     boolean matches(room r);
}

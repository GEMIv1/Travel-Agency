package com.example.Travel_agency.interfaces;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

@Repository
public interface IBookingRepository {

    public boolean book(String hotel_name,String roomType, String username, LocalDate startDate, LocalDate endDate, String payAmount);

}

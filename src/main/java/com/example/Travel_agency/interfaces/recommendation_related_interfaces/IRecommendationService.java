package com.example.Travel_agency.interfaces.recommendation_related_interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.bookingHotel;
import com.example.Travel_agency.entities.event;

@Service
public interface IRecommendationService {
    public String recommend(List<bookingHotel> bookings, List<event> events);
}

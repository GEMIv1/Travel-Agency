package com.example.Travel_agency.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class bookingHotel {
    private String location;
    private String hotelName;
    private String roomType;
    private Double price;
    private String userBooked;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    public bookingHotel(){
        
    }

    @JsonCreator
    public bookingHotel(@JsonProperty("hotelName") String hotelName,@JsonProperty("roomType") String roomType,@JsonProperty("userBooked") String userBooked ,@JsonProperty("startDate") LocalDate startDate,@JsonProperty("endDate") LocalDate endDate,@JsonProperty("price") double price, @JsonProperty("location") String location) {
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.price = price;
        this.userBooked = userBooked;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public String getUserBooked() {
        return userBooked;
    }

    public void setUserBooked(String userBooked) {
        this.userBooked = userBooked;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String location) {
        this.location = location;
    }

    public String getLocation(){
        return location;
    }


}

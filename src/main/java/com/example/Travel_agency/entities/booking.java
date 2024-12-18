package com.example.Travel_agency.entities;

import java.time.LocalDate;

public class booking {
    private String hotelName;
    private String roomType;
    private Double price;
    private String userBooked;
    private LocalDate startDate;
    private LocalDate endDate;

    public booking(String hotelName, String roomType, Double price, String userBooked, LocalDate startDate, LocalDate endDate) {
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.price = price;
        this.userBooked = userBooked;
        this.startDate = startDate;
        this.endDate = endDate;
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
    // violates S
    public boolean overlaps(LocalDate start, LocalDate end) {
        return (start.isBefore(endDate) || start.isEqual(endDate)) &&
               (end.isAfter(startDate) || end.isEqual(startDate));
    }
}

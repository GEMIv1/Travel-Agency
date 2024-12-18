package com.example.Travel_agency.entities;

import java.time.LocalDate;

public class booking {
    private String userBooked;
    private LocalDate startDate;
    private LocalDate endDate;

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

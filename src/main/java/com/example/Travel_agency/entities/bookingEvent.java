package com.example.Travel_agency.entities;

public class bookingEvent {
    private event eventBooked;
    private String userBooked;

    public bookingEvent() {
    }

    public bookingEvent(String userBooked, event eventBooked){
        this.userBooked = userBooked;
        this.eventBooked = eventBooked;
    }

    public void setEventBooked(event e){this.eventBooked = e;}
    public event getEventBooked(){return eventBooked;}

    public void setUserBooked(String userBooked){this.userBooked = userBooked;}
    public String getUserBooked(){return userBooked;}

}

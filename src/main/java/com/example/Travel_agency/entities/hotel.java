package com.example.Travel_agency.entities;

import java.util.List;

public class hotel {
    private String location;
    private String name;
    private List<room> rooms;

    public hotel(){}

    public hotel(String name, List<room> rooms, String location){
        this.location = location;
        this.rooms = rooms;
        this.name = name;
    }


    public void setName(String name){this.name = name;}

    public String getName(){return name;}

    public void setLocation(String location){this.location = location;}

    public String getLocation(){return location;}

    public List<room> getRooms(){
        return rooms;
    }

    public void setRooms(List<room> rooms){
        this.rooms = rooms;
    }

}

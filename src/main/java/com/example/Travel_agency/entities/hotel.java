package com.example.Travel_agency.entities;

import java.util.List;

public class hotel {
    private String name;
    private List<room> rooms;

    public hotel(){}

    public hotel(String name, List<room> rooms){
        this.rooms = rooms;
        this.name = name;
    }


    public void setName(String name){this.name = name;}

    public String getName(){return name;}

    public List<room> getRooms(){
        return rooms;
    }

    public void setRooms(List<room> rooms){
        this.rooms = rooms;
    }

}

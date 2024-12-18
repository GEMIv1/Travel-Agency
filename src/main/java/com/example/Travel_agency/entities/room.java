package com.example.Travel_agency.entities;


public class room {
    private Double price;
    private String type;

    

    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    public Double getPrice(){
        return price;
    }

}

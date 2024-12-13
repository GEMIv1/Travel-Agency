package com.example.Travel_agency.services;

import entities.user;

public class creatingAccountVerification {
    
    public boolean verify(user u){

        if(u.getEmail().contains("@gmail.com")){
            return true;
        }
        return false;
    }


}

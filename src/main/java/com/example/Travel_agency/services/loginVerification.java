package com.example.Travel_agency.services;

import java.util.List;

import entities.user;

public class loginVerification {
    
    public boolean perform(List<user> users, String username, String password){
        boolean isCorrectPassword = false;
        boolean isCorrectUsername = false;
        for(user u: users){
            if(password.equals(u.getPassword())){
                isCorrectPassword = true;
            }
            if(username.equals(u.getUserName())){
                isCorrectUsername = true;
            }
        }
        
        if(isCorrectPassword && isCorrectUsername){
            return true;
        }

        return false;
    }

}

package com.example.Travel_agency.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;

@Service
public class loginVerification {
    
    public user perform(List<user> users, String username, String password){
        boolean isCorrectPassword = false;
        boolean isCorrectUsername = false;
        for(user u: users){
            if(password.equals(u.getPassword())){
                isCorrectPassword = true;
            }
            if(username.equals(u.getUserName())){
                isCorrectUsername = true;
            }
            if(isCorrectPassword && isCorrectUsername){
                
                return u;
            }
        }
        
        
        return null;
    }

}

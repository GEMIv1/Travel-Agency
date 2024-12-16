package com.example.Travel_agency.services;
import java.util.List;
import com.example.Travel_agency.entities.user;
import java.util.UUID;


public class ResetPasswordVerification {

    public boolean perform(List<user> allUsers, String username, String oldPassword) {
        String guid = UUID.randomUUID().toString().replace("-", "");
        for(user u: allUsers){
            if(u.getUserName().equals(username) && u.getPassword().equals(oldPassword)){
                u.setPassword(guid);
                return true;
            }
        }
        return false;
    }
    
}

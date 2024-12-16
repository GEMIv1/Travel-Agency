package com.example.Travel_agency.services;
import java.util.List;
import com.example.Travel_agency.entities.user;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ResetPasswordVerification {

    public user perform(List<user> allUsers, String username, String oldPassword) {
        String guid = UUID.randomUUID().toString().replace("-", "");
        for(user u: allUsers){
            if(u.getUserName().equals(username) && u.getPassword().equals(oldPassword)){
                u.setPassword(guid);
                return u;
            }
        }
        return null;
    }
    
}

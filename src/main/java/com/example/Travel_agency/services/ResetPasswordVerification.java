package com.example.Travel_agency.services;
import java.util.List;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IUserRepository;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ResetPasswordVerification {

    public user perform(List<user> allUsers, String username, String oldPassword, IUserRepository userRepository) {
        String guid = UUID.randomUUID().toString().replace("-", "");
        for(user u: allUsers){
            if(u.getUserName().equals(username) && u.getPassword().equals(oldPassword)){
                u.setPassword(guid);
                userRepository.updatePassword(u);
                return u;
            }
        }
        return null;
    }
    
}

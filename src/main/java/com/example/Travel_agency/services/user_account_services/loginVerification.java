package com.example.Travel_agency.services.user_account_services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.ILoginService;

@Service
public class loginVerification implements ILoginService{
    
    @Override
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

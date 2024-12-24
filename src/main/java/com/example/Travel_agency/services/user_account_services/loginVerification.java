package com.example.Travel_agency.services.user_account_services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IService;

@Service("loginVerification")
public class loginVerification implements IService {

    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if (!"login".equals(operationType)) {
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }

        List<user> users = (List<user>) params.get("users");
        String username = (String) params.get("username");
        String password = (String) params.get("password");

        return login(users, username, password);


    }


    
    public user login(List<user> users, String username, String password){
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

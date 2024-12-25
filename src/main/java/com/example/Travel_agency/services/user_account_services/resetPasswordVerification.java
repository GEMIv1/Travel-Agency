package com.example.Travel_agency.services.user_account_services;
import java.util.List;
import java.util.Map;

import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service("resetPasswordVerification")
public class resetPasswordVerification implements IService{

    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {

        if (!"resetPassword".equals(operationType)) {
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }
        List<user> allUsers = (List<user>) params.get("allUsers");
        String username = (String) params.get("username");
        String oldPassword = (String) params.get("oldPassword");
        String token = (String) params.get("token");
        IUserRepository userRepository = (IUserRepository) params.get("userRepository");
        IUserAuthRepository authRepository = (IUserAuthRepository) params.get("authRepository");



        if (token != null) {
            return resetLoggedIn(allUsers, username, oldPassword,userRepository, authRepository, token);
        } else {
            return resetLoggedIn(allUsers, username, oldPassword, userRepository,null, null);
        }

    }

    
    
    
    
    
    public user resetLoggedIn(List<user> allUsers, String username, String oldPassword, IUserRepository userRepository,IUserAuthRepository authRepository, String token) {
        
        String guid = UUID.randomUUID().toString().replace("-", "");

        if(token!=null){
            user usr = authRepository.getUserWithToken(token, allUsers);
            if(usr.getUserName().equals(username)){
                usr.setPassword(guid);
                userRepository.updatePassword(usr);
            }
            return usr;

           
        }        
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

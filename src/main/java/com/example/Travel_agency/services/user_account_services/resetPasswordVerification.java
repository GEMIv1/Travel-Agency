package com.example.Travel_agency.services.user_account_services;
import java.util.List;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.IResetPasswordService;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.IUserRepository;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class resetPasswordVerification implements IResetPasswordService{

    @Override
    public user perform(List<user> allUsers, String username, String oldPassword, IUserRepository userRepository,IUserAuthRepository authRepository, String token) {
        
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

    @Override
    public user perform(List<user> allUsers, String username, String oldPassword, IUserRepository userRepository) {
        return perform(allUsers, username, oldPassword, userRepository,null, null);
    }
    
}

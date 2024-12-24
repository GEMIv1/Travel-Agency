package com.example.Travel_agency.services.user_account_services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;

@Service("updateUsername")
public class updateUsername implements IService {
    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if(!"updateUsername".equals(operationType)){
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }
        String newUsername = (String) params.get("newUsername");
        String token = (String) params.get("token");
        List<user> allUsers = (List<user>) params.get("allUsers");
        IUserAuthRepository userAuthRepository = (IUserAuthRepository) params.get("userAuthRepository");
        IUserRepository userRepository = (IUserRepository) params.get("userRepository");
        return updateUsername(newUsername,token,userAuthRepository,userRepository,allUsers);
    }

    public boolean updateUsername( String newUsername,String token, IUserAuthRepository userAuthRepository, IUserRepository userRepository, List<user> allUsers){
        user user = userAuthRepository.getUserWithToken(token, allUsers);
        user.setUserName(newUsername);
        userRepository.updateUsername(user);
        return true;
    }

}

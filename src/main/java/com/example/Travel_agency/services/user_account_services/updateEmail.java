package com.example.Travel_agency.services.user_account_services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;

@Service("updateEmail")
public class updateEmail implements IService {
    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if(!"updateEmail".equals(operationType)){
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }
        String newEmail = (String) params.get("newEmail");
        String token = (String) params.get("token");
        List<user> allUsers = (List<user>) params.get("allUsers");
        IUserRepository userRepository = (IUserRepository) params.get("userRepository");
        IUserAuthRepository userAuthRepository = (IUserAuthRepository) params.get("userAuthRepository");
        return updateEmail(newEmail,token,allUsers,userRepository,userAuthRepository);

        

    }
    public boolean updateEmail(String newEmail,String token, List<user> allUsers , IUserRepository userRepository, IUserAuthRepository userAuthRepository){
        user user = userAuthRepository.getUserWithToken(token, allUsers);
        user.setEmail(newEmail);
        userRepository.updateEmail(user);
        return true;
    }

}

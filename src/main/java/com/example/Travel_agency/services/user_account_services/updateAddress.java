package com.example.Travel_agency.services.user_account_services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;

@Service("updateAddress")
public class updateAddress implements IService {
    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if(!"updateAddress".equals(operationType)){
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }
        String newAddress = (String) params.get("newAddress");
        String token = (String) params.get("token");
        List<user> allUsers = (List<user>) params.get("allUsers");
        IUserAuthRepository userAuthRepository = (IUserAuthRepository) params.get("userAuthRepository");
        IUserRepository userRepository = (IUserRepository) params.get("userRepository");
        return changeAddress(newAddress,token,allUsers,userAuthRepository,userRepository);
    }

    public boolean changeAddress(String newAddress,String token, List<user> allUsers,IUserAuthRepository userAuthRepository,IUserRepository userRepository){
        user user = userAuthRepository.getUserWithToken(token, allUsers);
        user.setAddress(newAddress);
        userRepository.updateAddress(user);
        return true;
    }

}

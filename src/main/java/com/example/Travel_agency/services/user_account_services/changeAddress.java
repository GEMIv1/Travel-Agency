package com.example.Travel_agency.services.user_account_services;

import java.util.List;
import java.util.Map;

import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;

public class changeAddress implements IService{

    private IUserAuthRepository userAuthRepository;
    public changeAddress(IUserAuthRepository userAuthRepository){
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if(!"changeAddress".equals(operationType)){
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }
        String newAddress = (String) params.get("newAddress");
        String token = (String) params.get("token");
        IUserRepository userRepository = (IUserRepository) params.get("userRepository");
        List<user> allUsers = (List<user>) params.get("allUsers");
        
        return changeAddress(newAddress,token,allUsers,userRepository);
    }

    public boolean changeAddress(String newAddress,String token, List<user> allUsers, IUserRepository userRepository){
        user user = userAuthRepository.getUserWithToken(token, allUsers);
        user.setAddress(newAddress);
        userRepository.updateAddress(user);
        return true;
    }
    


}

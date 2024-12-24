package com.example.Travel_agency.services.user_account_services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;

@Service("updatePhone")
public class updatePhone implements IService {
    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if(!"updatePhone".equals(operationType)){
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }
        String newPhone = (String) params.get("newPhone");
        String token = (String) params.get("token");
        List<user> allUsers = (List<user>) params.get("allUsers");
        IUserRepository userRepository = (IUserRepository) params.get("userRepository");
        IUserAuthRepository userAuthRepository = (IUserAuthRepository) params.get("userAuthRepository");


        return updatePhone(newPhone,token,allUsers,userRepository,userAuthRepository);
    }
    public boolean updatePhone(String newPhone,String token, List<user> allUsers , IUserRepository userRepository, IUserAuthRepository userAuthRepository){
        user user = userAuthRepository.getUserWithToken(token, allUsers);
        user.setPhoneNumber(newPhone);
        userRepository.updatePhone(user);
        return true;
    }

}

package com.example.Travel_agency.services.user_account_services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;

@Service("logout")
public class logout implements IService {
    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if(!"logout".equals(operationType)){
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }
        String token = (String) params.get("token");
        IUserAuthRepository userAuthRepository = (IUserAuthRepository) params.get("userAuthRepository");
        return logout(token,userAuthRepository);
    }

   public boolean logout(String token, IUserAuthRepository userAuthRepository){
        userAuthRepository.deleteUserAuth(token);
        return true;
    }

}

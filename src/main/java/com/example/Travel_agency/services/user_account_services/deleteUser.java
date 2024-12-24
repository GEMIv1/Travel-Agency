package com.example.Travel_agency.services.user_account_services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;

@Service("deleteUser")
public class deleteUser implements IService {
    @Override
    public Object performOperation(String operationType, Map<String, Object> params) {
        if(!"deleteUser".equals(operationType)){
            throw new UnsupportedOperationException("Unsupported operation: " + operationType);
        }
        String token = (String) params.get("token");
        IUserAuthRepository userAuthRepository = (IUserAuthRepository) params.get("userAuthRepository");
        List<user> allUsers = (List<user>) params.get("allUsers");
        IUserRepository userRepository = (IUserRepository) params.get("userRepository");
        return deleteUser(token,userAuthRepository,allUsers,userRepository);
    }

    public boolean deleteUser(String token, IUserAuthRepository userAuthRepository, List<user> allUsers, IUserRepository userRepository){
        user user = userAuthRepository.getUserWithToken(token, allUsers);
        allUsers.remove(user);
        userAuthRepository.deleteUserAuth(token);
        userRepository.deleteUser(user);
        return true;
    }

}

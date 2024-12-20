package com.example.Travel_agency.stored_data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.entities.userAuth;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.IUserAuthRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class authenticationDatabase implements IUserAuthRepository{

    private final String path = "D:\\SDA_Project\\userAuthentication.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void saveUserAuth(userAuth u) {
        try {
            List<userAuth> userAuths = getAllUserAuth();
            userAuths.add(u);

            objectMapper.writeValue(new File(path), userAuths);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<userAuth> getAllUserAuth() {

        List<userAuth> userAuths = new ArrayList<>();
        try {
            File file = new File(path);
            if (file.exists()) {
                userAuths = objectMapper.readValue(file, new TypeReference<ArrayList<userAuth>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userAuths;
    }

    @Override
    public user getUserWithToken(String token, List<user> users) {
        
        List<userAuth> userAuths = getAllUserAuth();

        for(userAuth u: userAuths){
            if(u.getToken().equals(token)){
                for(user i:users){
                    if(i.getUserName().equals(u.getUsername()))
                    return i;
                }
            }
        }
        return null;
    }

}

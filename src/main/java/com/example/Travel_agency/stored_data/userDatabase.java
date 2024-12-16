package com.example.Travel_agency.stored_data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IUserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class userDatabase implements IUserRepository {

    private final String  filePath = "D:\\SDA_Project\\userData.json";
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void saveUser(user u) {
        try {
            List<user> users = getAllUsers();
            users.add(u);  
            
            objectMapper.writeValue(new File(filePath), users);
        } catch (IOException e) {
            System.out.println("Error while saving user: " + e.getMessage());
        }
    }
    @Override
    public List<user> getAllUsers() {
        List<user> users = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                users = objectMapper.readValue(file, new TypeReference<ArrayList<user>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public user searchUsername(String username) {
        List<user> users = getAllUsers();
        for (user u : users) {
            if (u.getUserName().equals(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void updatePassword(String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePassword'");
    }

    private String userToString(user u){
        return String.join(",",
        u.getUserName(), u.getFName(), u.getLName(), u.getAddress(),
        u.getEmail(), u.getAge(), u.getPhoneNumber(), u.getPassword());
    }

    private user StringToUser(String line){
        String[] data = line.split(",");
        user u = new user();
        u.setUserName(data[0]);
        u.setFName(data[1]);
        u.setLName(data[2]);
        u.setAddress(data[3]);
        u.setEmail(data[4]);
        u.setAge(data[5]);
        u.setPhoneNumber(data[6]);
        u.setPassword(data[7]);
        return u;
    }

}

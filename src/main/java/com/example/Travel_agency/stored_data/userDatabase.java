package com.example.Travel_agency.stored_data;

import java.io.File;
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
    public void updatePassword(user u) {
        List<user> users = getAllUsers();
        for (user user : users) {
            if (user.getUserName().equals(u.getUserName())) {
                user.setPassword(u.getPassword());
            }
        }
        try {
            objectMapper.writeValue(new File(filePath), users);
        } catch (IOException e) {
            System.out.println("Error while updating password: " + e.getMessage());
        }
    }
    @Override
    public void updateEmail(user usr) {
        
        List<user> users = getAllUsers();
        for (user user : users) {
            if (user.getUserName().equals(usr.getUserName())) {
                user.setEmail(usr.getEmail());
            }
        }
        try {
            objectMapper.writeValue(new File(filePath), users);
        } catch (IOException e) {
            System.out.println("Error while updating email: " + e.getMessage());
        }
    }

    @Override
    public void updatePhone(user usr) {
        List<user> users = getAllUsers();
        for (user user : users) {
            if (user.getUserName().equals(usr.getUserName())) {
                user.setPhoneNumber(usr.getPhoneNumber());
            }
        }
        try {
            objectMapper.writeValue(new File(filePath), users);
        } catch (IOException e) {
            System.out.println("Error while updating phone: " + e.getMessage());
        }
    }
    @Override
    public void updateAddress(user usr) {
        List<user> users = getAllUsers();
        for (user user : users) {
            if (user.getUserName().equals(usr.getUserName())) {
                user.setAddress(usr.getAddress());
            }
        }
        try {
            objectMapper.writeValue(new File(filePath), users);
        } catch (IOException e) {
            System.out.println("Error while updating address: " + e.getMessage());
        }
    }
    @Override
    public void updateUsername(user usr) {
        List<user> users = getAllUsers();
        for (user user : users) {
            if (user.getUserName().equals(usr.getUserName())) {
                user.setUserName(usr.getUserName());
            }
        }
        try {
            objectMapper.writeValue(new File(filePath), users);
        } catch (IOException e) {
            System.out.println("Error while updating username: " + e.getMessage());
        }
    }
    @Override
    public void deleteUser(user usr) {
        List<user> users = getAllUsers();
        for (user user : users) {
            if (user.getUserName().equals(usr.getUserName())) {
                users.remove(user);
                break;
            }
        }
        try {
            objectMapper.writeValue(new File(filePath), users);
        } catch (IOException e) {
            System.out.println("Error while deleting user: " + e.getMessage());
        }
    }


 
 

}

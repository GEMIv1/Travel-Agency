package com.example.Travel_agency.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Travel_agency.entities.user;

@Repository
public interface IUserRepository {

    public void saveUser(user u);
    public List<user> getAllUsers();
    public user searchUsername(String username);
    public void updatePassword(user u);
    public void updateEmail(user usr);
    public void updatePhone(user usr);
    public void updateAddress(user usr);
    public void updateUsername(user usr);
    public void deleteUser(user usr);

}

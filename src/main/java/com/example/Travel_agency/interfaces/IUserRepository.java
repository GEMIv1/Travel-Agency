package com.example.Travel_agency.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Travel_agency.entities.user;

@Repository
public interface IUserRepository {

    void saveUser(user u);
    List<user> getAllUsers();
    user searchUsername(String username);
    void updatePassword(user u);

}

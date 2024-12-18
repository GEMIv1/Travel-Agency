package com.example.Travel_agency.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Travel_agency.entities.userAuth;;


@Repository
public interface IUserAuthRepository {

    public void saveUserAuth(userAuth u);
    public List<userAuth> getAllUserAuth();

}

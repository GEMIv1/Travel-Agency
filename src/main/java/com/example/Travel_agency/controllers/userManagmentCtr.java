package com.example.Travel_agency.controllers;
import com.example.Travel_agency.services.creatingAccountVerification;

import entities.user;

public class userManagmentCtr {
    private user u;
    private creatingAccountVerification createAccount;


    userManagmentCtr(){
        u = new user();
        createAccount = new creatingAccountVerification();
    }

    public void setUserDetails(String name){
        
    }
    
    public boolean createUser(){
        return createAccount.verify(u);
    }







}

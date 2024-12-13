package com.example.Travel_agency.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Travel_agency.services.creatingAccountVerification;

import entities.user;

@RestController
public class userManagmentCtr {
    private user u;
    private creatingAccountVerification createAccount;


    userManagmentCtr(){
        u = new user();
        createAccount = new creatingAccountVerification();
    }

    @PutMapping("/travel_agency/updateUser")
    public void setUserDetails(
        @RequestParam String name,
        @RequestParam String phoneNumber,
        @RequestParam String address,
        @RequestParam String email,
        @RequestParam String age) {
        u.setName(name);
        u.setPhoneNumber(phoneNumber);
        u.setAddress(address);
        u.setEmail(email);
        u.setAge(age);
    }
    
    @GetMapping("/travel_agency/createAccount")
    public boolean createUserAccount(){
        if(createAccount.verify(u)){
            System.out.println("Account created");
            return true;
        }
        return false;
    }

    /*@GetMapping("/travel_agency/getUser")
    public user returnUserData(){
        return u;
    }*/







}

package com.example.Travel_agency.controllers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Travel_agency.services.creatingAccountVerification;
import com.example.Travel_agency.services.loginVerification;

import entities.user;

@RestController
public class userManagmentCtr {
    private List<user> users;// database till now
    private creatingAccountVerification createAccount;
    private loginVerification login;


    userManagmentCtr(){
        users = new ArrayList();
        createAccount = new creatingAccountVerification();
        login = new loginVerification();
    }

    @PutMapping("/travel_agency/set_user_details")
    public void setUserDetails(
        @RequestParam String username,
        @RequestParam String Fname,
        @RequestParam String Lname,
        @RequestParam String phoneNumber,
        @RequestParam String address,
        @RequestParam String email,
        @RequestParam String age,
        @RequestParam String password) {
        user u = new user();

        u.setFName(Fname);
        u.setLName(Lname);
        u.setPhoneNumber(phoneNumber);
        u.setAddress(address);
        u.setEmail(email);
        u.setAge(age);
        u.setPassword(password);
        u.setUserName(username);

        if(createUserAccount(u)){
            users.add(u);
        }else{
            System.out.println("Incorrect Information");
        }
    }
    
    @GetMapping("/travel_agency/get_creating_account_status")
    public boolean createUserAccount(user u){
        if(createAccount.perform(u)){
            //send account created successfully
            return true;
        }
        return false;
    }

    @GetMapping("/travel_agency/get_user_details")
    public List<user> returnUserData(){
        return users;
    }

    @PutMapping("/travel_agency/login")
    public boolean loginVerification(@RequestParam String username, @RequestParam String password){
        
        if(login.perform(users,username,password)){
            // send login successfully
            return true;
        }

        return false;
    }

}

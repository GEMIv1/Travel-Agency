package com.example.Travel_agency.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Travel_agency.services.ResetPasswordVerification;
import com.example.Travel_agency.services.creatingAccountVerification;
import com.example.Travel_agency.services.loginVerification;
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.entities.userAuth;
import com.example.Travel_agency.interfaces.IMessageRepository;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;

@RestController
public class userManagmentCtr {

/* Make interface for each service */
    @Autowired
    private creatingAccountVerification createAccount;
    @Autowired
    private loginVerification login;
    @Autowired 
    private IUserRepository userRepository;
    @Autowired
    private ResetPasswordVerification restPass;
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    private IUserAuthRepository userAuthRepository;

    @PostMapping("/travel_agency/create_account")
    public void setUserDetails(
        @RequestParam String username,
        @RequestParam String Fname,
        @RequestParam String Lname,
        @RequestParam String phoneNumber,
        @RequestParam String address,
        @RequestParam String email,
        @RequestParam String age,
        @RequestParam String password,
        @RequestParam String channel) {
        
        user u = new user();
        u.setFName(Fname);
        u.setLName(Lname);
        u.setPhoneNumber(phoneNumber);
        u.setAddress(address);
        u.setEmail(email);
        u.setAge(age);
        u.setPassword(password);
        u.setUserName(username);
        u.setChannel(channel);
        

        if(createAccount.perform(u)){
            userRepository.saveUser(u);
            messageRepository.saveMessage(new message(channel, "Dear: " + username + " the account created successfully", "NOTSENT"));
            System.out.println("User added");
        }else{
            System.out.println("Incorrect Information");
        }
    }

    @PostMapping("/travel_agency/login")
    public boolean loginVerification(@RequestParam String username, @RequestParam String password){ 
        
        user u = login.perform(userRepository.getAllUsers(),username,password);

        if(u != null){
            String newToken = UUID.randomUUID().toString();
            userAuthRepository.saveUserAuth(new userAuth(newToken, username));
            messageRepository.saveMessage(new message(u.getChannel(), "Dear: " + username + " login done successfully", "NOTSENT"));
            return true;
        }
        return false;
    }
    @PostMapping("/travel_agency/reset_password")
    public boolean ResetPassword(@RequestParam String username, @RequestParam String oldPassword, @RequestParam(required = false) String channelOverride, @RequestParam(required = false) String token){
        
        user u;
        if(token != null){// reset password during the current login session 
            u = restPass.perform(userRepository.getAllUsers(), username, oldPassword, userRepository, userAuthRepository, token);
        }
        else{// reset before logging in
            u = restPass.perform(userRepository.getAllUsers(),username,oldPassword,userRepository);
        }
        

        if(u!=null){
            System.out.println("test123");
            String channelToUse = (channelOverride != null) ? channelOverride : u.getChannel();
            messageRepository.saveMessage(new message(channelToUse, "Dear: " + username + " new password is "+ u.getPassword(), "NOTSENT"));
            return true;
        }
        return false;
    }
    @GetMapping("/travel_agency/get_user_details")
    public List<user> getAllUsers(){
        return userRepository.getAllUsers();
    }
    @PostMapping("/travel_agency/logout")
    public boolean logout(@RequestParam String token, @RequestParam String username){


        return false;
    }
}
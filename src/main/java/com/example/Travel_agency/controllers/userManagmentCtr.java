package com.example.Travel_agency.controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.entities.userAuth;
import com.example.Travel_agency.interfaces.controllers_interfaces.IUserManagmentCtr;
import com.example.Travel_agency.interfaces.message_related_interfaces.IMessageRepository;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.ICreateAccountService;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.ILoginService;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.IResetPasswordService;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.user_account_related_interfaces.IUserRepository;

@Controller
public class userManagmentCtr implements IUserManagmentCtr{


    @Autowired
    private ICreateAccountService createAccount;
    @Autowired
    private ILoginService login;
    @Autowired 
    private IUserRepository userRepository;
    @Autowired
    private IResetPasswordService restPass;
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    private IUserAuthRepository userAuthRepository;

    
    public void setUserDetails(
         String username,
         String Fname,
         String Lname,
         String phoneNumber,
         String address,
         String email,
         String age,
         String password,
         String channel) {
        
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
        
        
        if(createAccount.perform(u,userRepository.getAllUsers())){
            userRepository.saveUser(u);
            messageRepository.saveMessage(new message(channel, "Dear: " + username + " the account created successfully", "NOTSENT"));
            System.out.println("User added");
        }else{
            System.out.println("Incorrect Information");
        }
    }

    
    public boolean loginVerification( String username,  String password){ 
        
        user u = login.perform(userRepository.getAllUsers(),username,password);

        if(u != null){
            String newToken = UUID.randomUUID().toString();
            userAuthRepository.saveUserAuth(new userAuth(newToken, username));
            messageRepository.saveMessage(new message(u.getChannel(), "Dear: " + username + " login done successfully", "NOTSENT"));
            return true;
        }
        return false;
    }
    
    public boolean ResetPassword( String username,  String oldPassword,  String channelOverride,  String token){
        
        user u;
        if(token != null){// reset password during the current login session 
            u = restPass.perform(userRepository.getAllUsers(), username, oldPassword, userRepository, userAuthRepository, token);
        }
        else{// reset before logging in
            u = restPass.perform(userRepository.getAllUsers(),username,oldPassword,userRepository);
        }
        

        if(u!=null){
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
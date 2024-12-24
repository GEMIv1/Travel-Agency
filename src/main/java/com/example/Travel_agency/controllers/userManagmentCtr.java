package com.example.Travel_agency.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.entities.userAuth;
import com.example.Travel_agency.interfaces.IMessageRepository;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;
import com.example.Travel_agency.interfaces.controllers_interfaces.IUserManagmentCtr;

@Controller
public class userManagmentCtr implements IUserManagmentCtr{


    @Autowired 
    private IUserRepository userRepository;
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    private IUserAuthRepository userAuthRepository;
    @Autowired
    @Qualifier("resetPasswordVerification")
    private IService resetPasswordService;

    @Autowired
    @Qualifier("loginVerification")
    private IService loginVerificationService;

    @Autowired
    @Qualifier("creatingAccountVerification")
    private IService createAccountService;

    
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

        Map<String, Object> paramsCreateAccount = new HashMap<>();
        paramsCreateAccount.put("users", userRepository.getAllUsers());
        paramsCreateAccount.put("user", u);
        boolean isCreated = (boolean) createAccountService.performOperation("createAccount", paramsCreateAccount);
        
        
        if(isCreated){
            userRepository.saveUser(u);
            messageRepository.saveMessage(new message(channel, "Dear: " + username + " the account created successfully", "NOTSENT"));
            System.out.println("User added");
        }else{
            System.out.println("Incorrect Information");
        }
    }

    
    public boolean loginVerification(String username,  String password){ 

        Map<String, Object> paramsLogin = new HashMap<>();
        paramsLogin.put("users", userRepository.getAllUsers());
        paramsLogin.put("username", username);
        paramsLogin.put("password", password);

        
        user u = (user) loginVerificationService.performOperation("login",paramsLogin);

        if(u != null){
            String newToken = UUID.randomUUID().toString();
            userAuthRepository.saveUserAuth(new userAuth(newToken, username));
            messageRepository.saveMessage(new message(u.getChannel(), "Dear: " + username + " login done successfully", "NOTSENT"));
            return true;
        }
        return false;
    }
    
    public boolean ResetPassword( String username,  String oldPassword,  String channelOverride){
        Map<String, Object> paramsResetPassword = new HashMap<>();
        paramsResetPassword.put("allUsers", userRepository.getAllUsers());
        paramsResetPassword.put("username", username);
        paramsResetPassword.put("oldPassword", oldPassword);
        paramsResetPassword.put("userRepository", userRepository);
        paramsResetPassword.put("authRepository", userAuthRepository);

       
       
        user u = (user) resetPasswordService.performOperation("resetPassword", paramsResetPassword);
        
        

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
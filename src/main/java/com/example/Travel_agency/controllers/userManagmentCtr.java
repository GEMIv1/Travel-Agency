package com.example.Travel_agency.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Travel_agency.services.ResetPasswordVerification;
import com.example.Travel_agency.services.creatingAccountVerification;
import com.example.Travel_agency.services.loginVerification;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IUserRepository;


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

        if(createAccount.perform(u)){
            userRepository.saveUser(u);
            //send account created success
            System.out.println("User added");
        }else{
            System.out.println("Incorrect Information");
        }
    }

    public boolean loginVerification(@RequestParam String username, @RequestParam String password){ 
        if(login.perform(userRepository.getAllUsers(),username,password)){
            //send loing success email
            return true;
        }
        return false;
    }

    public boolean ResetPassword(@RequestParam String username, @RequestParam String oldPassword){
        if(restPass.perform(userRepository.getAllUsers(),username,oldPassword)){
            //send email/sms with the new password
            return true;
        }
        return false;
    }

}

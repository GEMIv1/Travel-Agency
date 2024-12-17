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
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IMessageRepository;
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
            message.id++;
            messageRepository.saveMessage(new message(channel, "Dear: " + username + "the account created successfully", "NOTSENT"));
            System.out.println("User added");
        }else{
            System.out.println("Incorrect Information");
        }
    }

    @PostMapping("/travel_agency/login")
    public boolean loginVerification(@RequestParam String username, @RequestParam String password, @RequestParam(required = false) String channelOverride){ 
        
        user u = login.perform(userRepository.getAllUsers(),username,password);
        String channelToUse = (channelOverride != null) ? channelOverride : u.getChannel();

        if(u != null){
            message.id++;
            
            messageRepository.saveMessage(new message(channelToUse, "Dear: " + username + " login done successfully", "NOTSENT"));
            return true;
        }
        return false;
    }

    @PostMapping("/travel_agency/reset_password")
    public boolean ResetPassword(@RequestParam String username, @RequestParam String oldPassword, @RequestParam(required = false) String channelOverride){
        
        user u = restPass.perform(userRepository.getAllUsers(),username,oldPassword,userRepository);
        String channelToUse = (channelOverride != null) ? channelOverride : u.getChannel();
        System.out.println(u.getChannel());

        if(u!=null){
            message.id++;
            messageRepository.saveMessage(new message(channelToUse, "Dear: " + username + "new password is "+ u.getPassword(), "NOTSENT"));
            return true;
        }
        return false;
    }
    @GetMapping("/travel_agency/get_user_details")
    public List<user> getAllUsers(){
        return userRepository.getAllUsers();
    }

}

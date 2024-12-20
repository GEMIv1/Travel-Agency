package com.example.Travel_agency.interfaces.controllers_interfaces;

import org.springframework.stereotype.Service;

@Service
public interface IUserManagmentCtr {
    public void setUserDetails(
         String username,
         String Fname,
         String Lname,
         String phoneNumber,
         String address,
         String email,
         String age,
         String password,
         String channel);
    public boolean loginVerification( String username,  String password);
    public boolean ResetPassword( String username,  String oldPassword,  String channelOverride,  String token);

}

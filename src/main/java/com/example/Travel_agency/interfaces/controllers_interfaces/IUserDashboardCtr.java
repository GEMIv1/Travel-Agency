package com.example.Travel_agency.interfaces.controllers_interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IBookingRepository;

@Service
public interface IUserDashboardCtr {

    public user getUserDetails(String token);
    public List<message> getMessages(String token);
    public List<IBookingRepository> getBookings(String token);
    public boolean changePassword(String token, String newPassword);
    public boolean changeEmail(String token, String newEmail);
    public boolean changePhone(String token, String newPhone);
    public boolean changeAddress(String token, String newAddress);
    public boolean deleteAccount(String token);
    public boolean changeUsername(String token, String newUsername);
    public void logout(String token) ;


    

}

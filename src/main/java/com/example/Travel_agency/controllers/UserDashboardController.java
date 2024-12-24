package com.example.Travel_agency.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.example.Travel_agency.entities.bookingEvent;
import com.example.Travel_agency.entities.bookingHotel;
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IBookingRepository;
import com.example.Travel_agency.interfaces.IMessageRepository;
import com.example.Travel_agency.interfaces.IService;
import com.example.Travel_agency.interfaces.IUserAuthRepository;
import com.example.Travel_agency.interfaces.IUserRepository;
import com.example.Travel_agency.interfaces.controllers_interfaces.IUserDashboardCtr;

import java.util.regex.Matcher;

@Controller
public class UserDashboardController implements IUserDashboardCtr {

    @Autowired
    private IUserAuthRepository authRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    @Qualifier("bookingHotelDatabase")
    private IBookingRepository<bookingHotel> bookingHotelRepository;
    @Autowired
    @Qualifier("bookingEventDataBase")
    private IBookingRepository<bookingEvent> bookingEventRepository;
    @Autowired
    @Qualifier("resetPasswordVerification")
    private IService resetPasswordService;
    @Autowired
    @Qualifier("updateEmail")
    private IService changeEmailService;
    @Autowired
    @Qualifier("updatePhone")
    private IService changePhoneService;
    @Autowired
    @Qualifier("updateAddress")
    private IService changeAddressService;
    @Autowired
    @Qualifier("updateUsername")
    private IService changeUsernameService;
    @Autowired
    @Qualifier("deleteUser")
    private IService deleteAccountService;
    @Autowired
    @Qualifier("logout")
    private IService logoutService;





    public user getUserDetails(String token) {
        return authRepository.getUserWithToken(token, userRepository.getAllUsers());
    }

    public List<message> getMessages(String token) {
        user usr = authRepository.getUserWithToken(token, userRepository.getAllUsers());

        if(usr != null){
            List<message> res = new ArrayList<message>(); 
            List<message> allMessages = messageRepository.getAllMessage();
            String regex = "Dear " + Pattern.quote(usr.getUserName()) + ":.*";

            for(message m : allMessages){
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(m.getContent());
                if (matcher.matches()) {
                    res.add(m);
                }
            }
            return res;
        }

        return null;
    }

    public List<IBookingRepository> getBookings(String token) {
        user usr = authRepository.getUserWithToken(token, userRepository.getAllUsers());
        List<IBookingRepository> res = new ArrayList<IBookingRepository>();

        if(usr != null){
            res.add(bookingHotelRepository);
            res.add(bookingEventRepository);
            return res;
        }

        return null;
    }

    public boolean changePassword(String token, String newPassword) {
        user usr = authRepository.getUserWithToken(token, userRepository.getAllUsers());
        if(usr != null){
            usr.setPassword(newPassword);
            userRepository.updatePassword(usr);
            messageRepository.saveMessage(new message(usr.getChannel(), "Dear " + usr.getUserName() + ": Your password has been changed successfully","NOT SENT"));
            return true;
        }
        return false;
    }

    public boolean changeEmail(String token, String newEmail) {
        user usr = authRepository.getUserWithToken(token, userRepository.getAllUsers());
        if(usr != null){
            changeEmailService.performOperation("updateEmail", Map.of("newEmail", newEmail, "token", token, "allUsers", userRepository.getAllUsers(), "userRepository", userRepository, "userAuthRepository", authRepository));
            messageRepository.saveMessage(new message(usr.getChannel(), "Dear " + usr.getUserName() + ": Your email has been changed successfully","NOT SENT"));
            return true;
        }
        return false;
    }

    public boolean changePhone(String token, String newPhone) {
        user usr = authRepository.getUserWithToken(token, userRepository.getAllUsers());
        if(usr != null){
            changePhoneService.performOperation("updatePhone", Map.of("newPhone", newPhone, "token", token, "allUsers", userRepository.getAllUsers(), "userRepository", userRepository, "userAuthRepository", authRepository));
            messageRepository.saveMessage(new message(usr.getChannel(), "Dear " + usr.getUserName() + ": Your phone number has been changed successfully","NOT SENT"));
            return true;
        }
        return false;
    }

    public boolean changeAddress(String token, String newAddress) {
        user usr = authRepository.getUserWithToken(token, userRepository.getAllUsers());
        if(usr != null){
            changeAddressService.performOperation("changeAddress", Map.of("newAddress", newAddress, "token", token, "allUsers", userRepository.getAllUsers(),"userRepository", userRepository, "userAuthRepository", authRepository));
            messageRepository.saveMessage(new message(usr.getChannel(), "Dear " + usr.getUserName() + ": Your address has been changed successfully","NOT SENT"));
            return true;
        }
        return false;
    }

    public boolean deleteAccount(String token) {
        user usr = authRepository.getUserWithToken(token, userRepository.getAllUsers());
        if(usr != null){
            deleteAccountService.performOperation("deleteAccount", Map.of("token", token, "allUsers", userRepository.getAllUsers(),"userRepository", userRepository, "userAuthRepository", authRepository));
            return true;
        }
        return false;
    }

    public boolean changeUsername(String token, String newUsername) {
        user usr = authRepository.getUserWithToken(token, userRepository.getAllUsers());
        if(usr != null){
            changeUsernameService.performOperation("updateUsername", Map.of("newUsername", newUsername, "token", token, "allUsers", userRepository.getAllUsers(),"userRepository", userRepository, "userAuthRepository", authRepository));
            messageRepository.saveMessage(new message(usr.getChannel(), "Dear " + usr.getUserName() + ": Your username has been changed successfully","NOT SENT"));
            return true;
        }
        return false;
    }

    public void logout(String token) {
        logoutService.performOperation("logout", Map.of("token", token, "IUserAuthRepository", authRepository));
    }
}

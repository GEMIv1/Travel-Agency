package com.example.Travel_agency;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Travel_agency.entities.event;
import com.example.Travel_agency.entities.hotel;
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IBookingRepository;
import com.example.Travel_agency.interfaces.controllers_interfaces.IEventManagmentCtr;
import com.example.Travel_agency.interfaces.controllers_interfaces.IHotelManagmentCtr;
import com.example.Travel_agency.interfaces.controllers_interfaces.IUserDashboardCtr;
import com.example.Travel_agency.interfaces.controllers_interfaces.IUserManagmentCtr;

@RestController
public class APIGateway {

    @Autowired
    private IUserManagmentCtr userManagmentCtr;
    @Autowired
    private IHotelManagmentCtr hotelManagmentCtr;
    @Autowired
    private IEventManagmentCtr eventManagmentCtr;
    @Autowired
    private IUserDashboardCtr userDashboardCtr;

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
        userManagmentCtr.setUserDetails(username, Fname, Lname, phoneNumber, address, email, age, password, channel);
    }

    @PostMapping("/travel_agency/login")
    public boolean loginVerification(@RequestParam String username, @RequestParam  String password) {
        return userManagmentCtr.loginVerification(username, password);
    }

    @PostMapping("/travel_agency/reset_password")
    public boolean ResetPassword(@RequestParam String username,@RequestParam  String oldPassword,@RequestParam (required = false) String channelOverride) {
        return userManagmentCtr.ResetPassword(username, oldPassword, channelOverride);
    }

    @GetMapping("/travel_agency/search_hotels")
    public List<hotel> searchHotels(
            @RequestParam String location,
            @RequestParam(required = false) String hotel_name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String roomType) {
        return hotelManagmentCtr.searchHotels(location, hotel_name, minPrice, maxPrice, roomType);
    }

    @PostMapping("/travel_agency/book_hotel")
    public boolean bookHotel(
            @RequestParam String location,
            @RequestParam String token,
            @RequestParam String hotelName,
            @RequestParam String roomType,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate  endDate){
        return hotelManagmentCtr.bookHotel(location, token, hotelName, roomType, startDate, endDate);
    }

    @GetMapping("/travel_agency/search_events")
    public List<event> searchEvents(
            @RequestParam(required = false) String event_name,
            @RequestParam(required = false) String event_date,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String category) {
       return eventManagmentCtr.searchEvents(event_name, event_date, location, price, category);
    }

    @PostMapping("/travel_agency/book_event")
    public void bookEvent(
            @RequestParam String token,
            @RequestParam String eventName) {
        eventManagmentCtr.bookEvent(token, eventName);
    }

    @PostMapping("/travel_agency/dashboard/change_password")
    public boolean changePassword(
            @RequestParam String token,
            @RequestParam String newPassword) {
        return userDashboardCtr.changePassword(token, newPassword);
    }

    @PostMapping("/travel_agency/dashboard/change_email")
    public boolean changeEmail(
            @RequestParam String token,
            @RequestParam String newEmail) {
        return userDashboardCtr.changeEmail(token, newEmail);
    }

    @PostMapping("/travel_agency/dashboard/change_phone")
    public boolean changePhone(
            @RequestParam String token,
            @RequestParam String newPhone) {
        return userDashboardCtr.changePhone(token, newPhone);
    }

    @PostMapping("/travel_agency/dashboard/change_username")
    public boolean changeUsername(
            @RequestParam String token,
            @RequestParam String newUsername) {
        return userDashboardCtr.changeUsername(token, newUsername);
    }

    @PostMapping("/travel_agency/dashboard/change_address")
    public boolean changeAddress(
            @RequestParam String token,
            @RequestParam String newAddress) {
        return userDashboardCtr.changeAddress(token, newAddress);
    }

    @GetMapping("/travel_agency/dashboard/get_bookings")
    public List<IBookingRepository> getBookings(
            @RequestParam String token) {
        return userDashboardCtr.getBookings(token);
    }

    @GetMapping("/travel_agency/dashboard/get_user_details")
    public user getUserDetails(
            @RequestParam String token) {
        return userDashboardCtr.getUserDetails(token);
    }

    @GetMapping("/travel_agency/dashboard/get_messages")
    public List<message> getMessages(
            @RequestParam String token) {
        return userDashboardCtr.getMessages(token);
    }

    @PostMapping("/travel_agency/dashboard/delete_account")
    public boolean deleteAccount(
            @RequestParam String token) {
        return userDashboardCtr.deleteAccount(token);
    }

    @PostMapping("/travel_agency/dashboard/logout")
    public void logout(
            @RequestParam String token) {
         userDashboardCtr.logout(token);
    }

    



    



}

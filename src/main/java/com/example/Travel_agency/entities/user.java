package com.example.Travel_agency.entities;

import org.springframework.web.bind.annotation.RequestParam;

public class user {
    private String userName;
    private String Firstname;
    private String LastName;
    private String address;
    private String email;
    private String age;
    private String phoneNumber;
    private String password;
    private String channel;


    public user(){}

    public user(    String username,
        String Fname,
         String Lname,
         String phoneNumber,
         String address,
         String email,
         String age,
         String password,
         String channel){

            this.userName = username;
            this.Firstname = Firstname;
            this.LastName = LastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
            this.age = age;
            this.password = password;
            this.channel = channel;
         }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }


    public String getFName() {
        return Firstname;
    }

    public void setFName(String name) {
        this.Firstname = name;
    }

    public String getLName() {
        return LastName;
    }

    public void setLName(String name) {
        this.LastName = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

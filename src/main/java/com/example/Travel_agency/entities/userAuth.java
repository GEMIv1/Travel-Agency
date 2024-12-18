package com.example.Travel_agency.entities;


public class userAuth{

    String token;
    String username;

    public userAuth(String token, String name){
        this.token = token;
        this.username = name;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
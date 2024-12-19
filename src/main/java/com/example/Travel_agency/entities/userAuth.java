package com.example.Travel_agency.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class userAuth{

    String token;
    String username;

    public userAuth() {
    }

    @JsonCreator
    public userAuth(@JsonProperty("token") String token,@JsonProperty("username")  String name){
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
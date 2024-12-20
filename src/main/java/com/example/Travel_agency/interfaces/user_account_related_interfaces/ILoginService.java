package com.example.Travel_agency.interfaces.user_account_related_interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;

@Service
public interface ILoginService {
    public user perform(List<user> users, String username, String password);
}

package com.example.Travel_agency.interfaces.user_account_related_interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;

@Service
public interface IResetPasswordService {
    public user perform(List<user> allUsers, String username, String oldPassword, IUserRepository userRepository,IUserAuthRepository authRepository, String token);
    public user perform(List<user> allUsers, String username, String oldPassword, IUserRepository userRepository);
}

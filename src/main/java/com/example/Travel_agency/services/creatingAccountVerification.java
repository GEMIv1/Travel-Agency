package com.example.Travel_agency.services;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.user;

@Service
public class creatingAccountVerification{
    

    public boolean perform(user u){

        String phoneNumber = u.getPhoneNumber();
        String email = u.getEmail();
        String firstName = u.getFName();
        String lastName = u.getLName();
        String pass = u.getPassword();

        boolean isValidEmail = false;
        boolean isValidPhoneNumber = true;
        boolean isValidFname = true;
        boolean isValidLname = true;
        boolean isValidPassword = false;
        Set<Character> specialChars = Set.of('@', '!', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '_', '=');


        if(email.contains("@gmail.com")){            
            isValidEmail = true;
        }
        
        for (char c : phoneNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                isValidPhoneNumber = false;
                break;
            }
        }
        
        for (char c : firstName.toCharArray()) {
            if (!Character.isDigit(c)) {
                if(specialChars.contains(c)){
                    isValidFname = false;
                    break;
                }                
            }
        }

        for (char c : lastName.toCharArray()) {
            if (!Character.isDigit(c)) {
                if(specialChars.contains(c)){
                    isValidLname = false;
                    break;
                }                
            }
        }

        if((int)pass.length()>=8){
            boolean isUpper = false;
            boolean isLower = false;
            boolean isSpecialChar = false;
            boolean isDigit = false;

            for(char c: pass.toCharArray()){
                if(Character.isDigit(c))isDigit = true;
                if(Character.isUpperCase(c))isUpper = true;
                if(Character.isLowerCase(c))isLower = true;
                if(specialChars.contains(c))isSpecialChar = true;
                if(isUpper && isLower && isSpecialChar && isDigit){isValidPassword = true;break;}
            }
            
            System.out.println(isUpper);
            System.out.println(isLower);
            System.out.println(isSpecialChar);
            System.out.println(isDigit);
            System.out.println(isValidEmail);
            System.out.println(isValidPassword);
            System.out.println(isValidPhoneNumber);
            System.out.println(isValidFname);
            System.out.println(isValidLname);

        }

        if(isValidEmail && isValidPhoneNumber && isValidFname && isValidLname && isValidPassword){
            return true;
        }

        return false;
    }
}

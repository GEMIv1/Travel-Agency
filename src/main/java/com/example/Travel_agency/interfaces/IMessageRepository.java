package com.example.Travel_agency.interfaces;

import java.util.List;

import com.example.Travel_agency.entities.message;



public interface IMessageRepository {

    void saveMessage(message u);
    List<message> getAllMessage();

}

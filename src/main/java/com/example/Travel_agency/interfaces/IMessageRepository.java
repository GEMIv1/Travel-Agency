package com.example.Travel_agency.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Travel_agency.entities.message;


@Repository
public interface IMessageRepository {

    void saveMessage(message u);
    void updateMessage(message u);
    List<message> getAllMessage();

}

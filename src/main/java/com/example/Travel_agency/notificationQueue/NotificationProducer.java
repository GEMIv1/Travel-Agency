package com.example.Travel_agency.notificationQueue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.example.Travel_agency.entities.message;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NotificationProducer {

    private static final String FILE_PATH = "D:\\SDA_Project\\messageData.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void produceNotificationsFromFile() {
        try {
            List<message> messages = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<message>>() {});
            for (message msg : messages) {
                System.out.println("Adding message to the queue: " + msg);
                NotificationQueue.addMessage(msg);
            }
        } catch (IOException e) {
            System.out.println("Error reading messages from file: " + e.getMessage());
        }
    }
}

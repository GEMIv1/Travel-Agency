package com.example.Travel_agency.notificationQueue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.example.Travel_agency.entities.message;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NotificationProducer {

    private static final String FILE_PATH = "D:\\SDA_Project\\messageData.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void produceNotificationsFromFile() {
        try {

            JsonNode rootNode = objectMapper.readTree(new File(FILE_PATH));
            
            for (JsonNode node : rootNode) {
                message msg = parseMessage(node);
                if (msg != null) {
                    System.out.println("Adding message to the queue: " + msg.getContent());
                    NotificationQueue.addMessage(msg);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading messages from file: " + e.getMessage());
        }
    }

    private message parseMessage(JsonNode node) {
        String channel = node.get("channel").asText();
        String content = node.get("content").asText();
        String status = node.get("status").asText();
        

      return new message(channel, content,status);
        

    }
}

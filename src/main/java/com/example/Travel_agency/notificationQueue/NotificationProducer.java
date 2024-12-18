package com.example.Travel_agency.notificationQueue;

import java.io.File;
import java.io.IOException;
import com.example.Travel_agency.entities.message;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NotificationProducer extends Thread {
    private static final String FILE_PATH = "D:\\SDA_Project\\messageData.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                JsonNode rootNode = objectMapper.readTree(new File(FILE_PATH));

                for (JsonNode node : rootNode) {
                    message msg = parseMessage(node);
                    if (msg != null && "NOTSENT".equalsIgnoreCase(msg.getStatus())) {
                        System.out.println("Adding message to the queue: " + msg.getContent());
                        NotificationQueue.addMessage(msg);
                    } else {
                        System.out.println("No new messages or message is already sent.");
                    }
                }
                Thread.sleep(1000); 
            } catch (IOException | InterruptedException e) {
                System.out.println("Error reading messages from file: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private message parseMessage(JsonNode node) {
        String channel = node.get("channel").asText();
        String content = node.get("content").asText();
        String status = node.get("status").asText();
        long id = node.get("id").asLong();
        return new message(channel, content, status, id);
    }
}


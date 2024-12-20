package com.example.Travel_agency.stored_data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.interfaces.message_related_interfaces.IMessageRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class messageDatabase implements IMessageRepository{


    private String filePath = "D:\\SDA_Project\\messageData.json";
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void saveMessage(message newMessage) {
        try {
            List<message> messages = getAllMessage();

            long nextId = messages.isEmpty() ? 1 : messages.get(messages.size() - 1).getId() + 1;
            newMessage.setId(nextId);

            messages.add(newMessage);

            objectMapper.writeValue(new File(filePath), messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<message> getAllMessage() {
        List<message> messages = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                messages = objectMapper.readValue(file, new TypeReference<ArrayList<message>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public synchronized void updateMessage(message u) {
        try {
            List<message> messages = getAllMessage();
    
            boolean updated = false;
    
            for (message m : messages) {
                if (m.getId()== u.getId()) {
                    m.setStatus(u.getStatus());
                    updated = true;
                    break;
                }
            }
    
            if (!updated) {
                messages.add(u);
            }
    
            objectMapper.writeValue(new File(filePath), messages);
    

        } catch (IOException e) {
            System.err.println("Failed to update JSON file: " + e.getMessage());
        }
    }
    

}

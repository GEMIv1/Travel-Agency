package com.example.Travel_agency.stored_data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.entities.user;
import com.example.Travel_agency.interfaces.IMessageRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class messageDatabase implements IMessageRepository{


    private String filePath = "D:\\SDA_Project\\messageData.json";
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void saveMessage(message m) {
        
        try {
            List<message> messages = getAllMessage();
            messages.add(m);  
            
            objectMapper.writeValue(new File(filePath), messages);
        } catch (IOException e) {
            System.out.println("Error while saving user: " + e.getMessage());
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
    public void updateMessage(message u) {
        /*try {
            List<message> messages =  getAllMessage();

            for (message m : messages) {
                if(m.getId() == u.getId()){
                    m.setStatus(u.getStatus());
                }
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), messages);
            System.out.println("Message successfully updated in the JSON file.");
        } catch (IOException e) {
            System.err.println("Failed to update JSON file: " + e.getMessage());
        }*/
    }

}

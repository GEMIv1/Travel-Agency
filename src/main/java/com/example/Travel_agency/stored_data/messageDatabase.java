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


    private String filePath = "C:\\Users\\ALRWOAD LAPTOB\\OneDrive\\Desktop\\Travel_agency1\\SDA_Project\\messageData.json";
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void saveMessage(message newMessage) {
        try {
            List<message> messages = getAllMessage();

            // Set the ID based on the last message's ID, or 1 if the list is empty
            long nextId = messages.isEmpty() ? 1 : messages.get(messages.size() - 1).getId() + 1;
            newMessage.setId(nextId);

            // Add the new message
            messages.add(newMessage);

            // Serialize the updated list back to the file
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

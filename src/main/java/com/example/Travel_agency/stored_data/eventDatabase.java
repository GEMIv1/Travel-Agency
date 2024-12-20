package com.example.Travel_agency.stored_data;

import com.example.Travel_agency.entities.event;
import com.example.Travel_agency.interfaces.event_related_interfaces.IEventRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class eventDatabase implements IEventRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String path = "D:\\SDA_Project\\eventData.json";

        

    @Override
    public List<event> getEvents() {
        List<event> events = new ArrayList<>();
        try {
            File file = new File(path);
            if (file.exists()) {
                events = objectMapper.readValue(file, new TypeReference<List<event>>() {});
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return events;
    }
    
}

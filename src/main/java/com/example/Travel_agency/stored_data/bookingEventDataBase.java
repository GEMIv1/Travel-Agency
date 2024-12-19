package com.example.Travel_agency.stored_data;

import com.example.Travel_agency.entities.events;
import com.example.Travel_agency.interfaces.IEventRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class bookingEventDataBase implements IEventRepository {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String path = "C:\\Users\\ALRWOAD LAPTOB\\OneDrive\\Desktop\\Travel_agency1\\SDA_Project\\bookingEventData.json";

    @Override
    public List<events> getEvents(String event_name, String event_date, String location, String price, String category) {
        List<events> events = new ArrayList<>();
        try {
            File file = new File(path);
            if (file.exists()) {
                events = objectMapper.readValue(file, new TypeReference<List<events>>() {});
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return events;
    }

    @Override
    public void saveEvent(events event) {
        List<events> eventsList = new ArrayList<>();
        try {
            File file = new File(path);
            if (file.exists()) {
                eventsList = objectMapper.readValue(file, new TypeReference<List<events>>() {});
            }
            eventsList.add(event);
            objectMapper.writeValue(file, eventsList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

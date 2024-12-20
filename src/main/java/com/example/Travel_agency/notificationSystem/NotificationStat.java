package com.example.Travel_agency.notificationSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher; 

import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.interfaces.message_related_interfaces.IMessageRepository;

public class NotificationStat{

    private final IMessageRepository messageRepository;

    public NotificationStat(IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    
    public void printStat() {
        List<message> messages = messageRepository.getAllMessage();

        int sent = 0;
        int unsent = 0;
        Map<String, Integer> mostUsedChannel = new HashMap<String, Integer>();
        Map<String, Integer> mostRecievedUser = new HashMap<String, Integer>();
        
        String usernamePattern = "Dear:\\s(\\w+)";
        Pattern pattern = Pattern.compile(usernamePattern);

        for (message m : messages) {
            if (m.getStatus().equals("SENT")) {
                sent++;
            } else {
                unsent++;
            }

            mostUsedChannel.put(m.getChannel(), mostUsedChannel.getOrDefault(m.getChannel(), 0) + 1);

            Matcher matcher = pattern.matcher(m.getContent()); 
            if (matcher.find()) {
                String username = matcher.group(1); 
                mostRecievedUser.put(username, mostRecievedUser.getOrDefault(username, 0) + 1);
            }
        }

        System.out.println("Sent messages: " + sent);
        System.out.println("Unsent messages: " + unsent);
        System.out.println("Most used channels: " + mostUsedChannel);
        System.out.println("Most received users: " + mostRecievedUser);
    }
}

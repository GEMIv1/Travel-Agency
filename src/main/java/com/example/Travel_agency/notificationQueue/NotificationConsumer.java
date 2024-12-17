package com.example.Travel_agency.notificationQueue;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.interfaces.IMessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NotificationConsumer implements Runnable {

    private final  IMessageRepository messageRepository;

    private final ObjectMapper objectMapper;

    public NotificationConsumer(IMessageRepository mRepo){
        this.messageRepository = mRepo;
        objectMapper = new ObjectMapper();
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) { 
            try {
                message msg = NotificationQueue.getMessage();
                System.out.println("Processing message: " + msg);

                if ("email".equalsIgnoreCase(msg.getChannel())) {
                    sendEmail(msg);
                } else if ("sms".equalsIgnoreCase(msg.getChannel())) {
                    sendSMS(msg);
                }

                System.out.println("Message processed: " + msg);
            } catch (InterruptedException e) {
                System.out.println("Consumer thread interrupted. Exiting.");
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                System.out.println("Error while processing message: " + e.getMessage());
            }
        }
    }

    private void sendEmail(message msg) {
        System.out.println("Sending Email: " + msg.getContent());
        msg.setStatus("SENT");
    }
    
    private void sendSMS(message msg) {
        System.out.println("Sending SMS: " + msg.getContent());
        msg.setStatus("SENT");
    }
    

}


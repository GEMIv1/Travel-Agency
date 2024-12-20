package com.example.Travel_agency;

import com.example.Travel_agency.interfaces.message_related_interfaces.IMessageRepository;
import com.example.Travel_agency.notificationQueue.NotificationConsumer;
import com.example.Travel_agency.notificationQueue.NotificationProducer;
import com.example.Travel_agency.stored_data.messageDatabase;

public class NotificationApp {
    public static void main(String[] args) throws InterruptedException {
        
        IMessageRepository messageRepository = new messageDatabase();
        
        NotificationConsumer consumer = new NotificationConsumer(messageRepository);
        NotificationProducer producer = new NotificationProducer();

        consumer.start();
        producer.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down...");
            consumer.interrupt();
            producer.interrupt();
        }));
    }
}

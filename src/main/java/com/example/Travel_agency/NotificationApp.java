package com.example.Travel_agency;

import com.example.Travel_agency.interfaces.IMessageRepository;
import com.example.Travel_agency.notificationSystem.NotificationConsumer;
import com.example.Travel_agency.notificationSystem.NotificationProducer;
import com.example.Travel_agency.notificationSystem.NotificationStat;
import com.example.Travel_agency.stored_data.messageDatabase;

public class NotificationApp {
    public static void main(String[] args) throws InterruptedException {
        
        IMessageRepository messageRepository = new messageDatabase();


        NotificationStat notificationStat = new NotificationStat(messageRepository);
        notificationStat.printStat();
        
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

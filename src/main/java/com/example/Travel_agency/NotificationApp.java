package com.example.Travel_agency;
import com.example.Travel_agency.notificationQueue.NotificationConsumer;
import com.example.Travel_agency.notificationQueue.NotificationProducer;
import com.example.Travel_agency.notificationQueue.NotificationQueue;
import com.example.Travel_agency.stored_data.messageDatabase;
import com.example.Travel_agency.interfaces.IMessageRepository;

public class NotificationApp {
    public static void main(String[] args) throws InterruptedException {
        
        IMessageRepository messageRepository =  new messageDatabase();
        
        Thread consumerThread = new Thread(new NotificationConsumer(messageRepository));
        consumerThread.start();

        

        NotificationProducer producer = new NotificationProducer();
        producer.produceNotificationsFromFile();

        try {
            consumerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("Queue empty: " + NotificationQueue.isEmpty());

    }
}

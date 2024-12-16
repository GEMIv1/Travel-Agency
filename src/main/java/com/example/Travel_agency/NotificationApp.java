package com.example.Travel_agency;
import com.example.Travel_agency.notificationQueue.NotificationConsumer;
import com.example.Travel_agency.notificationQueue.NotificationProducer;
import com.example.Travel_agency.notificationQueue.NotificationQueue;
import com.example.Travel_agency.entities.message;

public class NotificationApp {
    public static void main(String[] args) throws InterruptedException {
        
        Thread consumerThread = new Thread(new NotificationConsumer());
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

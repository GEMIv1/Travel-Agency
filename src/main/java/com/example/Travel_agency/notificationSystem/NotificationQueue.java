package com.example.Travel_agency.notificationSystem;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.example.Travel_agency.entities.message;

public class NotificationQueue {
    private static final BlockingQueue<message> queue = new LinkedBlockingQueue<>();

    public  static void addMessage(message msg) {
        if (msg != null && "NOTSENT".equalsIgnoreCase(msg.getStatus())) {
            try {
                queue.put(msg);
                System.out.println("Message added to the queue: " + msg.getContent());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer interrupted while adding message.");
            }
        } else {
            System.out.println("Null or sent message cannot be added to the queue.");
        }
    }

    public  static message getMessage() throws InterruptedException {
        return queue.take();  
    }

    public  static boolean isEmpty() {
        return queue.isEmpty();
    }
}

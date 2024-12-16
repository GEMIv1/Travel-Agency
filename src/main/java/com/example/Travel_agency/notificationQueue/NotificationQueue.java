package com.example.Travel_agency.notificationQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.example.Travel_agency.entities.message;

public class NotificationQueue {
    private static final BlockingQueue<message> queue = new LinkedBlockingQueue<>();

    public static void addMessage(message msg) {
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer thread interrupted while adding message.");
        }
    }
    

    public static message getMessage() throws InterruptedException {
        return queue.take(); // Blocks until a message is available
    }

    public static boolean isEmpty() {
        return queue.isEmpty();
    }
}

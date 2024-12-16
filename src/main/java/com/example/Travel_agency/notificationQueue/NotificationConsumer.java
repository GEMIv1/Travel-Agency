package com.example.Travel_agency.notificationQueue;

import com.example.Travel_agency.entities.message;

public class NotificationConsumer implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) { // Check if thread is interrupted
            try {
                message msg = NotificationQueue.getMessage();
                System.out.println("Processing message: " + msg);

                // Simulate sending logic
                if ("email".equalsIgnoreCase(msg.getChannel())) {
                    sendEmail(msg);
                } else if ("sms".equalsIgnoreCase(msg.getChannel())) {
                    sendSMS(msg);
                }

                System.out.println("Message processed: " + msg);
            } catch (InterruptedException e) {
                System.out.println("Consumer thread interrupted. Exiting.");
                Thread.currentThread().interrupt(); // Set interrupted status
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


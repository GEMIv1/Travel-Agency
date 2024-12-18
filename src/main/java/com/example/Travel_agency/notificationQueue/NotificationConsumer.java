package com.example.Travel_agency.notificationQueue;

import com.example.Travel_agency.entities.message;
import com.example.Travel_agency.interfaces.IMessageRepository;

public class NotificationConsumer extends Thread {
    private final IMessageRepository messageRepository;

    public NotificationConsumer(IMessageRepository mRepo) {
        this.messageRepository = mRepo;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                message msg = NotificationQueue.getMessage();
                if (msg != null) {
                    System.out.println("Processing message: " + msg.getContent());
                    if ("email".equalsIgnoreCase(msg.getChannel())) {
                        sendEmail(msg);
                    } else if ("sms".equalsIgnoreCase(msg.getChannel())) {
                        sendSMS(msg);
                    }
                } else {
                    System.out.println("No message to process.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer thread interrupted.");
            }
        }
    }

    private void sendEmail(message msg) {
        System.out.println("Sending Email: " + msg.getContent());
        msg.setStatus("SENT");
        messageRepository.updateMessage(msg);
    }

    private void sendSMS(message msg) {
        System.out.println("Sending SMS: " + msg.getContent());
        msg.setStatus("SENT");
        messageRepository.updateMessage(msg);
    }
}


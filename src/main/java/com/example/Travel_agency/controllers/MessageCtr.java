package com.example.Travel_agency.controllers;

import com.example.Travel_agency.services.MessageService;
import entities.emailMessage;
import entities.smsMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
public class MessageCtr {
    private final MessageService messageService;


    public MessageCtr() {
        this.messageService = new MessageService();
    }

    @PostMapping("/travel_agency/sms")
    public ResponseEntity<String> sendMessage(@RequestBody smsMessage sms) {
        String response = messageService.SendSms(sms);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/travel_agency/email")
    public ResponseEntity<String> sendEmail(@RequestBody emailMessage email) {
        String response = messageService.sendEMAIL(email);
        return ResponseEntity.ok(response);
    }

}

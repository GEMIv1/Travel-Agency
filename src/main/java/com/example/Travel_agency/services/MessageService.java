package com.example.Travel_agency.services;
import entities.emailMessage;
import entities.smsMessage;



public class MessageService {

    public String SendSms(smsMessage sms)
    {
        sms.send();
        return "Sent sms to" + sms.getPhoneNumber();
    }

    public String sendEMAIL(emailMessage email)
    {
        email.send();
        return "Email sent to " + email.getRecipient();
    }
}

package com.example.Travel_agency.entities;

public class message {

    private String channel;
    private String content;
    private String status;

    public message(String channel, String content, String status) {
        this.channel = channel;
        this.content = content;
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
               "channel='" + channel + '\'' +
               ", content='" + content + '\'' +
               ", status='" + status + '\'' + "}";
    }
    



}

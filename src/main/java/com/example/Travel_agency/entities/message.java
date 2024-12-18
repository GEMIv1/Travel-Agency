package com.example.Travel_agency.entities;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class message {

    private String channel;
    private String content;
    private String status;
    private long exactId;

    @JsonCreator
    public message(@JsonProperty("channel") String channel, 
                   @JsonProperty("content") String content, 
                   @JsonProperty("status") String status) {
        this.channel = channel;
        this.content = content;
        this.status = status;
    }

    public message(String channel, String content, String status, long id){ 
        this.channel = channel;
        this.content = content;
        this.status = status;
        this.exactId = id;
    }

    public void setId(long id) {
        this.exactId = id;
    }

    public long getId() {
        return exactId;
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

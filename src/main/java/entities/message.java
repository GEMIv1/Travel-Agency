package entities;

abstract public class message {
    private String recipient;
    private String content;

    public message() {
    }

    public message(String recipient, String content) {
        this.recipient = recipient;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public abstract void send();
}


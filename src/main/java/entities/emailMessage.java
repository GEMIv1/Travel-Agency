package entities;

public class emailMessage extends message{
    private String subject;

    public emailMessage(String recipient,String subject, String content) {
        super(recipient, content);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void send() {
        System.out.println("Sending Email to " + getRecipient() +
                " with subject \"" + getSubject() + "\": " +
                getContent());
    }
}

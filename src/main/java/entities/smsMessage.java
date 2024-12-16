package entities;

public class smsMessage extends message{
    private String phoneNumber;

    public smsMessage(String phoneNumber, String content) {
        super(phoneNumber, content);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send() {
        //Logic needed to change

        System.out.println("Sending SMS to : " + getPhoneNumber() + ": " + getContent());
    }
}

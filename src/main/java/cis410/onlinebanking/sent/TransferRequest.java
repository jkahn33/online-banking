package cis410.onlinebanking.sent;

public class TransferRequest {
    private int from;
    private int to;
    private float amount;
    private String user;

    public TransferRequest(){}

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public float getAmount() {
        return amount;
    }

    public String getUser(){
        return user;
    }
}

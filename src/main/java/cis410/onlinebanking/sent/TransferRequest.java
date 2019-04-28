package cis410.onlinebanking.sent;

public class TransferRequest {
    private int from;
    private int to;
    private float amount;

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
}

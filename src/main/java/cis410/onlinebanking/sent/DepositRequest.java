package cis410.onlinebanking.sent;

public class DepositRequest {
    private int account;
    private float amount;

    public DepositRequest(){}

    public int getAccount() {
        return account;
    }

    public float getAmount() {
        return amount;
    }
}

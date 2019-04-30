package cis410.onlinebanking.sent;

public class DepositRequest {
    private String routing;
    private String account;
    private String holder;
    private String user;
    private int target;
    private float amount;

    public DepositRequest(){}

    public int getTarget() {
        return target;
    }

    public float getAmount() {
        return amount;
    }

    public String getRouting() {
        return routing;
    }

    public String getAccount() {
        return account;
    }

    public String getHolder() {
        return holder;
    }

    public String getUser(){
        return user;
    }
}

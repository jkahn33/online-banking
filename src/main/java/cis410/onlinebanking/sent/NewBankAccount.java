package cis410.onlinebanking.sent;

public class NewBankAccount {
    private String customerId;
    private int type;

    public NewBankAccount(){}

    public NewBankAccount(String customerId, int type) {
        this.customerId = customerId;
        this.type = type;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getType() {
        return type;
    }
}

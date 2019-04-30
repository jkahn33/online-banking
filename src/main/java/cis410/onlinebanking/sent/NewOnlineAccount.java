package cis410.onlinebanking.sent;

public class NewOnlineAccount {
    private String userName;
    private String password;
    private NewCustomer newCustomer;

    public NewOnlineAccount(){}

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public NewCustomer getNewCustomer() {
        return newCustomer;
    }
}

package cis410.onlinebanking.sent;

public class NewAddress {
    private String addr;
    private String city;
    private String state;
    private String country;
    private int zipcode;

    public NewAddress(){}

    public String getAddr() {
        return addr;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getZipcode() {
        return zipcode;
    }

    public String getState(){
        return state;
    }
}

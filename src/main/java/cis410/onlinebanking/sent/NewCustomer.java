package cis410.onlinebanking.sent;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class NewCustomer {
    private String name;
    private String dateOfBirth;
    private NewAddress address;
    private String phone;

    public NewCustomer(){}

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public NewAddress getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}

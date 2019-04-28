package cis410.onlinebanking.entities;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class OnlineAccount {

    @Id
    @NotNull
    private String userName;
    private String password;
    private Timestamp creation;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public OnlineAccount(@NotNull String userName, String password, Timestamp creation, Customer customer) {
        this.userName = userName;
        this.password = password;
        this.creation = creation;
        this.customer = customer;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Timestamp getCreation() {
        return creation;
    }

    public Customer getCustomer() {
        return customer;
    }
}

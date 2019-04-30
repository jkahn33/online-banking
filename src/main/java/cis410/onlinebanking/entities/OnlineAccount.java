package cis410.onlinebanking.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "online_account")
public class OnlineAccount {

    @Id
    @NotNull
    @Column(length=100)
    private String userName;
    private String password;
    private Timestamp creation;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public OnlineAccount(@NotNull String userName, String password, Timestamp creation, Customer customer) {
        this.userName = userName;
        this.password = password;
        this.creation = creation;
        this.customer = customer;
    }

    public OnlineAccount(){}

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

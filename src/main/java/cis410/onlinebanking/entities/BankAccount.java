package cis410.onlinebanking.entities;

import javax.persistence.*;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Customer customer;
    private AccountType type;
    private float balance;

    public BankAccount(){}

    public BankAccount(Customer customer, AccountType type, float balance) {
        this.customer = customer;
        this.type = type;
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public AccountType getType() {
        return type;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}

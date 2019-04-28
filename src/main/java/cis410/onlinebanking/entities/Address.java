package cis410.onlinebanking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String addr;
    private String city;
    private String country;
    private int zipcode;

    public Address(String addr, String city, String country, int zipcode) {
        this.addr = addr;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

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
}

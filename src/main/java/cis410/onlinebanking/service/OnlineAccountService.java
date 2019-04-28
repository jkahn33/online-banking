package cis410.onlinebanking.service;

import cis410.onlinebanking.ResponseObject;
import cis410.onlinebanking.dao.AddressDAO;
import cis410.onlinebanking.dao.BankAccountDAO;
import cis410.onlinebanking.dao.CustomerDAO;
import cis410.onlinebanking.dao.OnlineAccountDAO;
import cis410.onlinebanking.entities.*;
import cis410.onlinebanking.sent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class OnlineAccountService {
    private final OnlineAccountDAO accountDAO;
    private final PasswordEncoder passwordEncoder;
    private final CustomerDAO customerDAO;
    private final AddressDAO addressDAO;

    @Autowired
    public OnlineAccountService(OnlineAccountDAO accountDAO,
                                PasswordEncoder passwordEncoder,
                                CustomerDAO customerDAO,
                                AddressDAO addressDAO) {
        this.accountDAO = accountDAO;
        this.passwordEncoder = passwordEncoder;
        this.customerDAO = customerDAO;
        this.addressDAO = addressDAO;
    }

    public boolean verifyAccount(LoginAttempt attempt){
        Optional<OnlineAccount> onlineAccountOptional = accountDAO.findById(attempt.getUser());
        if(onlineAccountOptional.isPresent()){
            OnlineAccount account = onlineAccountOptional.get();
            return passwordEncoder.matches(attempt.getPass(), account.getPassword());
        }
        return false;
    }

    public ResponseObject createOnlineAccount(NewOnlineAccount account){
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());

        Optional<OnlineAccount> onlineAccountOptional = accountDAO.findById(account.getUserName());
        if(onlineAccountOptional.isPresent()){
            return new ResponseObject(false, "Username already in use.");
        }
        NewAddress newAddress = account.getNewCustomer().getAddress();
        NewCustomer newCustomer = account.getNewCustomer();

        Address address = new Address(newAddress.getAddr(), newAddress.getCity(), newAddress.getCountry(), newAddress.getZipcode());
        addressDAO.save(address);

        Customer customer = new Customer(newCustomer.getName(), newCustomer.getDateOfBirth(), address, newCustomer.getPhone());
        customerDAO.save(customer);

        OnlineAccount onlineAccount = new OnlineAccount(account.getUserName(), passwordEncoder.encode(account.getPassword()), currentTime, customer);
        accountDAO.save(onlineAccount);

        return new ResponseObject(true, "Successfully created new account.");
    }
}

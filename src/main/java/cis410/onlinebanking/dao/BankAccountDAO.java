package cis410.onlinebanking.dao;

import cis410.onlinebanking.entities.BankAccount;
import cis410.onlinebanking.entities.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankAccountDAO extends CrudRepository<BankAccount, Integer> {
    List<BankAccount> findAllByCustomer(Customer customer);
}

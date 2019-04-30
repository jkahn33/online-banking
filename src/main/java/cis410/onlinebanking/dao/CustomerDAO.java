package cis410.onlinebanking.dao;

import cis410.onlinebanking.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDAO extends CrudRepository<Customer, Integer> {
}

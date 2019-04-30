package cis410.onlinebanking.dao;

import cis410.onlinebanking.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressDAO extends CrudRepository<Address, Integer> {
}

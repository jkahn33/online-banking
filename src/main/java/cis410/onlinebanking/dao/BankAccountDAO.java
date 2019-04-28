package cis410.onlinebanking.dao;

import cis410.onlinebanking.entities.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountDAO extends CrudRepository<BankAccount, Integer> {
}

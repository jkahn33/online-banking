package cis410.onlinebanking.dao;

import cis410.onlinebanking.entities.OnlineAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineAccountDAO extends CrudRepository<OnlineAccount, String> {
}

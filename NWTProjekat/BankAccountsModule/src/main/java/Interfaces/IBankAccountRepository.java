package Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import Models.BankAccount;

public interface IBankAccountRepository extends CrudRepository<BankAccount, Integer> {

}

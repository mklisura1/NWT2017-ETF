package BankAccountService.Interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import BankAccountService.Models.BankAccount;

@Component
public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {

}

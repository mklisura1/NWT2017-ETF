package BankAccountsService.Interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import BankAccountsService.Models.BankAccount;

@Component
public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {

}

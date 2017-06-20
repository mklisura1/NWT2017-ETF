package BankAccountsService.Interfaces;

import BankAccountsService.Models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    @Query("select p from #{#entityName} p where p.bank_account_number = :number")
    BankAccount findByAccountNumber(@Param("number") String number);

}

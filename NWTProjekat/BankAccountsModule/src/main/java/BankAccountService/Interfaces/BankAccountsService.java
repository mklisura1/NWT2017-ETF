package BankAccountService.Interfaces;

import java.util.List;

import BankAccountService.Models.BankAccount;

public interface BankAccountsService {

	List<BankAccount> GetAllAccounts();
}

package BankAccountsService.Interfaces;

import java.util.List;

import BankAccountsService.Models.BankAccount;

public interface BankAccountsService {

	List<BankAccount> GetAllAccounts();
	BankAccount FindAccountById(int id);
	BankAccount SaveAccount(BankAccount bankAccount);
	BankAccount UpdateAccount(BankAccount bankAccount);
	void DeleteAccount(int id);	
}

package BankAccountsService.Interfaces;

import BankAccountsService.Models.BankAccount;

import java.util.List;

public interface BankAccountsService {

	List<BankAccount> GetAllAccounts();
	BankAccount FindAccountById(int id);
	BankAccount SaveAccount(BankAccount bankAccount);
	BankAccount UpdateAccount(BankAccount bankAccount);
	void DeleteAccount(int id);
	BankAccount FindAccountByNumber(String number);
}

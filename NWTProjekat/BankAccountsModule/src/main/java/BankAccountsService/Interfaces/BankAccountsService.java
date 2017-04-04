package BankAccountsService.Interfaces;

import java.util.List;

import BankAccountsService.Models.BankAccount;

public interface BankAccountsService {

	List<BankAccount> GetAllAccounts();
}

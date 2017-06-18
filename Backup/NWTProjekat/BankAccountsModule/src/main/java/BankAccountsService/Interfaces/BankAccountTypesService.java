package BankAccountsService.Interfaces;

import java.util.List;

import BankAccountsService.Models.BankAccountType;

public interface BankAccountTypesService {

	List<BankAccountType> GetAllAccountTypes();
	BankAccountType FindAccountTypeById(int id);
	BankAccountType SaveAccountType(BankAccountType bankAccountType);
	BankAccountType UpdateAccountType(BankAccountType bankAccountType);
	void DeleteAccountType(int id);	
}

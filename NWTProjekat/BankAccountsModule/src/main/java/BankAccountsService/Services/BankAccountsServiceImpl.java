package BankAccountsService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BankAccountsService.Interfaces.BankAccountRepository;
import BankAccountsService.Interfaces.BankAccountsService;
import BankAccountsService.Models.BankAccount;

@Service
public class BankAccountsServiceImpl implements BankAccountsService {

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	public List<BankAccount> GetAllAccounts() {
		return (List<BankAccount>) bankAccountRepository.findAll();
	}

}

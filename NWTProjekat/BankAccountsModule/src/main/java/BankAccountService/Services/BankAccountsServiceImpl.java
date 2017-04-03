package BankAccountService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BankAccountService.Interfaces.BankAccountRepository;
import BankAccountService.Interfaces.BankAccountsService;
import BankAccountService.Models.BankAccount;

@Service
public class BankAccountsServiceImpl implements BankAccountsService {

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	public List<BankAccount> GetAllAccounts() {
		return (List<BankAccount>) bankAccountRepository.findAll();
	}

}

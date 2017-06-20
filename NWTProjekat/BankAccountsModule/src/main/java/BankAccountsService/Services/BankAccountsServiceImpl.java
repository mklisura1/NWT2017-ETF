package BankAccountsService.Services;

import BankAccountsService.Interfaces.BankAccountRepository;
import BankAccountsService.Interfaces.BankAccountsService;
import BankAccountsService.Models.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountsServiceImpl implements BankAccountsService {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Override
	public List<BankAccount> GetAllAccounts() {
		return (List<BankAccount>) bankAccountRepository.findAll();
	}

	@Override
	public BankAccount FindAccountById(int id) {
		return bankAccountRepository.findOne(id);
	}

	@Override
	public BankAccount SaveAccount(BankAccount bankAccount) {
		return bankAccountRepository.save(bankAccount);
	}

	@Override
	public BankAccount UpdateAccount(BankAccount bankAccount) {
		return SaveAccount(bankAccount);
	}

	@Override
	public void DeleteAccount(int id) {
		bankAccountRepository.delete(id);
	}

	@Override
    public BankAccount FindAccountByNumber(String number){ return bankAccountRepository.findByAccountNumber(number);}
}

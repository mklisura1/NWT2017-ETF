package BankAccountsService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BankAccountsService.Interfaces.BankAccountTypeRepository;
import BankAccountsService.Interfaces.BankAccountTypesService;
import BankAccountsService.Models.BankAccountType;

@Service
public class BankAccountTypesServiceImpl implements BankAccountTypesService {
	
	@Autowired
	private BankAccountTypeRepository bankAccountTypeRepository;

	@Override
	public List<BankAccountType> GetAllAccountTypes() {
		return (List<BankAccountType>)bankAccountTypeRepository.findAll();
	}

	@Override
	public BankAccountType FindAccountTypeById(int id) {
		return bankAccountTypeRepository.findOne(id);
	}

	@Override
	public BankAccountType SaveAccountType(BankAccountType bankAccountType) {
		return bankAccountTypeRepository.save(bankAccountType);
	}

	@Override
	public BankAccountType UpdateAccountType(BankAccountType bankAccountType) {
		return SaveAccountType(bankAccountType);
	}

	@Override
	public void DeleteAccountType(int id) {
		bankAccountTypeRepository.delete(id);		
	}

}

package BankAccountsService.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import BankAccountsService.Interfaces.BankAccountsService;
import BankAccountsService.Models.BankAccount;

@RestController
@RequestMapping(value = "/accounts")
public class BankAccountsController {
	
	@Autowired
	private BankAccountsService bankAccountService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<BankAccount> GetAllAccounts(){
		return (List<BankAccount>) bankAccountService.GetAllAccounts();
	}
}

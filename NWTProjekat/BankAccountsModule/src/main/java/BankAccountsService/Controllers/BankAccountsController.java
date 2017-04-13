package BankAccountsService.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import BankAccountsService.Interfaces.BankAccountsService;
import BankAccountsService.Models.BankAccount;

@RestController
@RequestMapping(value = "/api")
public class BankAccountsController {

	@Autowired
	private BankAccountsService bankAccountService;

	@RequestMapping(value="/accounts", method = RequestMethod.GET)
	public ResponseEntity<List<BankAccount>> getAllAccounts() {
		List<BankAccount> bankAccounts = bankAccountService.GetAllAccounts();
		if (bankAccounts.isEmpty()) {
			return new ResponseEntity<List<BankAccount>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<BankAccount>>(bankAccounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
	public ResponseEntity<BankAccount> getBankAccount(@PathVariable("id") int id) {
		BankAccount bankAccount = bankAccountService.FindAccountById(id);
		if (bankAccount == null) {
			return new ResponseEntity<BankAccount>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<BankAccount>(bankAccount, HttpStatus.OK);
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccount bankAccount) {
		BankAccount account = bankAccountService.SaveAccount(bankAccount);
		return new ResponseEntity<BankAccount>(account, HttpStatus.OK);
	}
}

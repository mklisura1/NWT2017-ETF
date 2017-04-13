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

import BankAccountsService.Interfaces.BankAccountTypesService;
import BankAccountsService.Models.BankAccount;
import BankAccountsService.Models.BankAccountType;

@RestController
@RequestMapping(value="/api")
public class BankAccountTypesController {

	@Autowired
	private BankAccountTypesService bankAccountTypesService;
	
	@RequestMapping(value="/account/types", method = RequestMethod.GET)
	public ResponseEntity<List<BankAccountType>> getAllAccounts() {
		List<BankAccountType> bankAccountTypes = bankAccountTypesService.GetAllAccountTypes();
		if (bankAccountTypes.isEmpty()) {
			return new ResponseEntity<List<BankAccountType>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<BankAccountType>>(bankAccountTypes, HttpStatus.OK);
	}

	@RequestMapping(value = "/account/types/{id}", method = RequestMethod.GET)
	public ResponseEntity<BankAccountType> getBankAccountType(@PathVariable("id") int id) {
		BankAccountType bankAccountType = bankAccountTypesService.FindAccountTypeById(id);
		if (bankAccountType == null) {
			return new ResponseEntity<BankAccountType>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<BankAccountType>(bankAccountType, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/account/types", method = RequestMethod.POST)
	public ResponseEntity<BankAccountType> createAccount(@RequestBody BankAccountType bankAccountType) {
		BankAccountType accountType = bankAccountTypesService.SaveAccountType(bankAccountType);
		return new ResponseEntity<BankAccountType>(accountType, HttpStatus.OK);
	}
}

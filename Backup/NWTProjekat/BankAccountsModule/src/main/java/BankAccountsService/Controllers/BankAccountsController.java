package BankAccountsService.Controllers;

import BankAccountsService.Interfaces.BankAccountsService;
import BankAccountsService.Models.BankAccount;
import BankAccountsService.Templates.Transaction;
import BankAccountsService.Templates.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/accounts")
public class BankAccountsController {

	@Autowired
	private BankAccountsService bankAccountService;
	
	@Autowired
    private DiscoveryClient discoveryClient;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BankAccount>> getAllAccounts() {
		List<BankAccount> bankAccounts = bankAccountService.GetAllAccounts();
		if (bankAccounts.isEmpty()) {
			return new ResponseEntity<List<BankAccount>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<BankAccount>>(bankAccounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<BankAccount> getBankAccount(@PathVariable("id") int id) {
		BankAccount bankAccount = bankAccountService.FindAccountById(id);
		if (bankAccount == null) {
			return new ResponseEntity<BankAccount>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<BankAccount>(bankAccount, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccount bankAccount) {
		BankAccount account = bankAccountService.SaveAccount(bankAccount);
		return new ResponseEntity<BankAccount>(account, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}/transaction", method = RequestMethod.GET)
	public ResponseEntity<List<Transaction>> getAllTransactionsByBankAccountId(@PathVariable("id") int id) {
		BankAccount bankAccount = bankAccountService.FindAccountById(id);
		
		if (bankAccount == null) {
			System.out.println("Bank Account not found!");
			
			return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);
		}
		
	    String url = discoveryClient.getInstances("transactions").get(0).getUri().toString();
	    url += "/api/bankaccount/"+ id +"/transaction";
	    
	    System.out.println(url);
	    
		RestTemplate restTemplate = new RestTemplate();
	    /*HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    //HttpEntity<?> entity = new HttpEntity<Object>(,headers);*/
	    ResponseEntity<ArrayList> response = restTemplate.getForEntity(url,ArrayList.class);
	    //List<Transaction> transactions = (List<Transaction>) responseEntity.getBody();
	    
	    if(HttpStatus.OK != response.getStatusCode())
	    {
	    	System.out.println("Transactions not found!");
	    	
	    	return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);
	    	
	    }
	    
		List<Transaction> transactions = response.getBody();
		
		System.out.println(transactions);
	    
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}/user", method = RequestMethod.GET)
	public ResponseEntity<User> getBankAccountOwnerByAccountId(@PathVariable("id") int id) {
		BankAccount bankAccount = bankAccountService.FindAccountById(id);
		
		if (bankAccount == null) {
			System.out.println("Bank Account not found!");
			
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
	    String url = discoveryClient.getInstances("users").get(0).getUri().toString();
	    url += "/api/user/"+ bankAccount.getUser() + "/account";
	    
	    System.out.println(url);
	    
		RestTemplate restTemplate = new RestTemplate();
	    /*HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    //HttpEntity<?> entity = new HttpEntity<Object>(,headers);*/
	    ResponseEntity<User> response = restTemplate.getForEntity(url,User.class);
	    //List<Transaction> transactions = (List<Transaction>) responseEntity.getBody();
	    
	    if(HttpStatus.OK != response.getStatusCode())
	    {
	    	System.out.println("User not found!");
	    	
	    	return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	    	
	    }
	    
		User user = response.getBody();
	    
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	
	
	//------------------- Delete a Template --------------------------------------------------------
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<BankAccount> deleteBankAccount(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting Template with id " + id);
 
        bankAccountService.DeleteAccount(id);

        return new ResponseEntity<BankAccount>(HttpStatus.NO_CONTENT);
    }
}

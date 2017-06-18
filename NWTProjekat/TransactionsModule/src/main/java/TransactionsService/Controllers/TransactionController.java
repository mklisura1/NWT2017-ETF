package TransactionsService.Controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

//import TransactionsService.Models.BankAccount;
import TransactionsService.Models.Transaction;
import TransactionsService.Services.TransactionService;

@Controller
@RequestMapping(value = "/api")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;	
	
 /*   @Autowired
    private DiscoveryClient discoveryClient;*/

	// -------------------Retrieve All Transactions -----------------------------------------------------

	@RequestMapping(value = "/transaction", method = RequestMethod.GET)
	public ResponseEntity<List<Transaction>> listAllTransactions() {
		List<Transaction> transactions = (List<Transaction>) transactionService.listAllTransactions();
		if (transactions.isEmpty()) {
			return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	// -------------------Retrieve Transaction By
	// Id--------------------------------------------------------

	@RequestMapping(value = "/transaction/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") Integer id) {
		System.out.println("Fetching Transaction By Id: " + id);
		Transaction transaction = transactionService.getTransactionById(id);
		if (transaction == null) {
			System.out.println("Transaction not found");
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}
	
	// -------------------Retrieve All Transactions For BankAccount ---------------------------------

	@RequestMapping(value = "/bankaccount/{id}/transaction", method = RequestMethod.GET)
	public ResponseEntity<List<Transaction>> getAllTransactionsByBankAccountId(@PathVariable("id") Integer id) {
		List<Transaction> senderTransactions = (List<Transaction>) transactionService.getTransactionsBySenderId(id);
		List<Transaction> receiverTransactions = (List<Transaction>) transactionService.getTransactionsByReceiverId(id);
		if (senderTransactions.isEmpty() && receiverTransactions.isEmpty()) {
			return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);
		}
		
		senderTransactions.addAll(receiverTransactions);
		
		System.out.println("Velicina liste:" + senderTransactions.size());
		
		return new ResponseEntity<List<Transaction>>(senderTransactions, HttpStatus.OK);
	}
	
	// -------------------Retrieve Transaction By Payment Id ---------------------------------

	@RequestMapping(value = "/payment/{id}/transaction", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> getTransactionByPaymentId(@PathVariable("id") Integer id) {
		Transaction transaction = transactionService.getTransactionByPaymentId(id);
		
		if (transaction == null) {
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}

	// -------------------Create
	// Transaction--------------------------------------------------------

	@RequestMapping(value = "/transaction/", method = RequestMethod.POST)
	public ResponseEntity<Void> createTransaction(@RequestBody Transaction transaction/*,
			UriComponentsBuilder ucBuilder*/) {

		System.out.println("Creating Transaction");

		transactionService.saveTransaction(transaction);
		
	    /*String url = discoveryClient.getInstances("bankaccounts").get(0).getUri().toString();
	    url += "/api/account";
	    
	    System.out.println(url);
	    
	    BankAccount bankAccount = new BankAccount();
	    
	    bankAccount.setBank_account_id(transaction.getBank_account_sender_id());
	    bankAccount.setCredit_amount();
	    
		RestTemplate restTemplate = new RestTemplate();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity<?> entity = new HttpEntity<Object>(transaction,headers);
	    ResponseEntity<Object> responseEntity =    restTemplate.exchange(url, HttpMethod.POST, entity, Object.class);
	     */

		/*ServiceInstance instance = loadBalancer.choose("payments");

		LOG.info("URL: " + instance.getUri());
		String url = instance.getUri() + "/api/payments?userId=" + id;
		LOG.info("GET Payments from URL: {}", url);

		ResponseEntity<Object>  response = restTemplate.getForEntity(url, Object.class);
		LinkedHashMap<String, String> objects = (LinkedHashMap<String, String>) response.getBody();
		List<PaymentModel> paymentModelList = (List<PaymentModel>) (Object) objects.get("content");
		LOG.info("Responseeee: {}", objects.get("content") );
		return paymentModelList;*/
		HttpHeaders headers = new HttpHeaders();
		/*headers.setLocation(
				ucBuilder.path("/transaction/{id}").buildAndExpand(transaction.getTransaction_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);*/
	    
	    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Delete Transaction ------------------------------------------------

	@RequestMapping(value = "/transaction/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Transaction> deleteTransaction(@PathVariable("id") Integer id) {

		System.out.println("Deleting User");

		Transaction transaction = transactionService.getTransactionById(id);
		if (transaction == null) {
			System.out.println("Transaction not found");
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}

		transactionService.deleteTransaction(id);
		return new ResponseEntity<Transaction>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Transactions ------------------------------------------------

	@RequestMapping(value = "/transaction/", method = RequestMethod.DELETE)
	public ResponseEntity<Transaction> deleteAllTransactions() {
		System.out.println("Deleting All Transactions");

		for (Transaction item : transactionService.listAllTransactions()) {
			transactionService.deleteTransaction(item.getTransaction_id());
		}
		return new ResponseEntity<Transaction>(HttpStatus.NO_CONTENT);
	}

}

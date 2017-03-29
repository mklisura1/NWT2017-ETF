package TransactionsService.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import TransactionsService.Models.Transaction;
import TransactionsService.Services.TransactionService;

@Controller
@RequestMapping(value = "/api")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;

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

	// -------------------Create
	// Transaction--------------------------------------------------------

	@RequestMapping(value = "/transaction/", method = RequestMethod.POST)
	public ResponseEntity<Void> createTransaction(@RequestBody Transaction transaction,
			UriComponentsBuilder ucBuilder) {

		System.out.println("Creating Transaction");

		transactionService.saveTransaction(transaction);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/transaction/{id}").buildAndExpand(transaction.getTransaction_id()).toUri());
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

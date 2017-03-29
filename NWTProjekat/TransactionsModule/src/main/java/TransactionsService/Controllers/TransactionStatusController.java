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

import TransactionsService.Models.TransactionStatus;
import TransactionsService.Services.TransactionStatusService;

@Controller
@RequestMapping(value = "/api")
public class TransactionStatusController {
	@Autowired
	private TransactionStatusService transactionStatusService;

	// -------------------Retrieve All Transaction Statuses -------------------------------------------
	
	@RequestMapping(value = "/transaction/status", method = RequestMethod.GET)
	public ResponseEntity<List<TransactionStatus>> listAllTransactionStatuses() {
		List<TransactionStatus> transactionStatuses = (List<TransactionStatus>) transactionStatusService.listAllTransactionStatuses();
		if (transactionStatuses.isEmpty()) {
			
			return new ResponseEntity<List<TransactionStatus>>(HttpStatus.NO_CONTENT);
			
		}
		return new ResponseEntity<List<TransactionStatus>>(transactionStatuses, HttpStatus.OK);
	}

	// -------------------Retrieve Single Transaction Status -----------------------------------------

	@RequestMapping(value = "/transaction/status/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionStatus> getTransactionStatus(@PathVariable("id") Integer id) {
		
		System.out.println("Fetching Transaction Status");
		
		TransactionStatus transactionStatus = transactionStatusService.getTransactionStatusById(id);
		if (transactionStatus == null) {
			System.out.println("TransactionStatus not found");
			return new ResponseEntity<TransactionStatus>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TransactionStatus>(transactionStatus, HttpStatus.OK);
	}

	// -------------------Create TransactionStatus -------------------------------------------------------------

	@RequestMapping(value = "/transaction/status/", method = RequestMethod.POST)
	public ResponseEntity<Void> createTransactionStatus(@RequestBody TransactionStatus transactionStatus, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating TransactionStatus");

		transactionStatusService.saveTransactionStatus(transactionStatus);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/transaction/status/{id}").buildAndExpand(transactionStatus.getTransaction_status_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update Transaction Status ---------------------------------------------------------------

	@RequestMapping(value = "/transaction/status/{id}", method = RequestMethod.PUT)
	public ResponseEntity<TransactionStatus> updateTransactionStatus(@PathVariable("id") Integer id, @RequestBody TransactionStatus transactionStatus) {
		System.out.println("Updating TransactionStatus");

		TransactionStatus currentTransactionStatus = transactionStatusService.getTransactionStatusById(id);

		if (currentTransactionStatus == null) {
			System.out.println("TransactionStatus not found");
			return new ResponseEntity<TransactionStatus>(HttpStatus.NOT_FOUND);
		}

		currentTransactionStatus.setTransaction_status_name(transactionStatus.getTransaction_status_name());

		transactionStatusService.updateTransactionStatus(currentTransactionStatus);
		return new ResponseEntity<TransactionStatus>(currentTransactionStatus, HttpStatus.OK);
	}

	// ------------------- Delete Transaction Status ------------------------------------------------------------

	@RequestMapping(value = "/transaction/status/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<TransactionStatus> deleteTransactionStatus(@PathVariable("id") Integer id) {
		System.out.println("Deleting TransactionStatus");

		TransactionStatus transactionStatus = transactionStatusService.getTransactionStatusById(id);
		if (transactionStatus == null) {
			System.out.println("TransactionStatus not found");
			return new ResponseEntity<TransactionStatus>(HttpStatus.NOT_FOUND);
		}

		transactionStatusService.deleteTransactionStatus(id);
		return new ResponseEntity<TransactionStatus>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All TransactionStatuses --------------------------------------------------------

	@RequestMapping(value = "/transaction/status/", method = RequestMethod.DELETE)
	public ResponseEntity<TransactionStatus> deleteAllTransactionStatuses() {
		System.out.println("Deleting All TransactionStatuses");

		for (TransactionStatus item : transactionStatusService.listAllTransactionStatuses()) {
			transactionStatusService.deleteTransactionStatus(item.getTransaction_status_id());
		}
		return new ResponseEntity<TransactionStatus>(HttpStatus.NO_CONTENT);
	}
}

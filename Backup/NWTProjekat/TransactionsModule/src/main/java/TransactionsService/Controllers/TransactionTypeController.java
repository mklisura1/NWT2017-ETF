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

import TransactionsService.Models.TransactionType;
import TransactionsService.Services.TransactionTypeService;

@Controller
@RequestMapping(value="/api")
public class TransactionTypeController 
{
	@Autowired
	private TransactionTypeService transactionTypeService;
	
	//-------------------Retrieve All TransactionTypes--------------------------------------------------------
    
    @RequestMapping(value = "/transaction/type", method = RequestMethod.GET)
    public ResponseEntity<List<TransactionType>> listAllTransactionTypes() {
        List<TransactionType> transactionTypes = (List<TransactionType>) transactionTypeService.listAllTransactionTypes();
        if(transactionTypes.isEmpty()){
            return new ResponseEntity<List<TransactionType>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<TransactionType>>(transactionTypes, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve TransactionType--------------------------------------------------------
     
    @RequestMapping(value = "/transaction/type/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionType> getTransactionType(@PathVariable("id") Integer id) {
        System.out.println("Fetching TransactionType");
        TransactionType transactionType = transactionTypeService.getTransactionTypeById(id);
        
        if (transactionType == null) {
            System.out.println("TransactionType not found");
            return new ResponseEntity<TransactionType>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<TransactionType>(transactionType, HttpStatus.OK);
    }
    
	// -------------------Create TransactionType -------------------------------------------------------------

	@RequestMapping(value = "/transaction/type/", method = RequestMethod.POST)
	public ResponseEntity<Void> createTransactionType(@RequestBody TransactionType transactionType, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating TransactionType");

		transactionTypeService.saveTransactionType(transactionType);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/transaction/type/{id}").buildAndExpand(transactionType.getTransaction_type_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
    
    //------------------- Delete TransactionType --------------------------------------------------------
     
    @RequestMapping(value = "/transaction/type/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TransactionType> deleteTransactionType(@PathVariable("id") Integer id) {
    	
        System.out.println("Deleting TransactionType");
 
        TransactionType transactionType = transactionTypeService.getTransactionTypeById(id);
        
        if (transactionType == null) {
            System.out.println("TransactionType not found");
            return new ResponseEntity<TransactionType>(HttpStatus.NOT_FOUND);
        }

        transactionTypeService.deleteTransactionType(id);
        return new ResponseEntity<TransactionType>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All TransactionTypes --------------------------------------------------------
    
    @RequestMapping(value = "/transaction/type/", method = RequestMethod.DELETE)
    public ResponseEntity<TransactionType> deleteAllTransactionTypes() {
        System.out.println("Deleting All TransactionTypes");
 
        for(TransactionType item: transactionTypeService.listAllTransactionTypes())
        {
        	transactionTypeService.deleteTransactionType(item.getTransaction_type_id());
        }
        return new ResponseEntity<TransactionType>(HttpStatus.NO_CONTENT);
    }
}

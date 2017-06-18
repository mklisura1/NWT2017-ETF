package TransactionsService.Repository;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import TransactionsService.Models.Transaction;

@Component
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
	
}
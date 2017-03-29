package TransactionsService.Services;

import java.util.List;

import TransactionsService.Models.Transaction;

public interface TransactionService 
{
	
	List<Transaction> listAllTransactions();

	Transaction getTransactionById(Integer id);

	List<Transaction> getTransactionsByUserId(Integer id);
    
	List<Transaction> getTransactionsByTypeId(Integer type_id);
	
	List<Transaction> getTransactionsByStatusId(Integer status_id);

	Transaction saveTransaction(Transaction transaction);

	void deleteTransaction(Integer id);
	
}
package TransactionsService.Services;

import java.util.Date;
import java.util.List;

import TransactionsService.Models.Transaction;

public interface TransactionService 
{
	
	List<Transaction> listAllTransactions();

	Transaction getTransactionById(Integer id);

	List<Transaction> getTransactionsByUserId(Integer id);
    
	List<Transaction> getTransactionsByTypeName(String type_name);
	
	List<Transaction> getTransactionsByStatusName(String status_name);
	
	Transaction getTransactionByPaymentId(Integer id);
	
	List<Transaction> getTransactionsBySenderId(Integer id);
	
	List<Transaction> getTransactionsByReceiverId(Integer id);
	
	List<Transaction> getTransactionsByDate(Date date);
	
	Transaction saveTransaction(Transaction transaction);

	void deleteTransaction(Integer id);
	
}
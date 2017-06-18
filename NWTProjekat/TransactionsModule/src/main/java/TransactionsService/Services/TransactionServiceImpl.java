package TransactionsService.Services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TransactionsService.Models.Transaction;
import TransactionsService.Repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService 
{
	@Autowired
	private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> listAllTransactions() 
    {
        return (List<Transaction>) transactionRepository.findAll();
    }

	public Transaction getTransactionById(Integer id)
	{
		return transactionRepository.findOne(id);
	}

	/*public List<Transaction> getTransactionsByUserId(Integer id)
	{
		
    	List<Transaction> userTransactions = new ArrayList<Transaction>();
    	
    	List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
    	
    	for(Transaction item : transactions)
    	{
    		if(item.getUser_id().equals(id)) 
    		{
    			userTransactions.add(item);
    		}
    	}
    	
    	return userTransactions;
		
	}*/
    
	public List<Transaction> getTransactionsByTypeName(String type_name)
	{
		
    	List<Transaction> typeTransactions = new ArrayList<Transaction>();
    	
    	List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
    	
    	for(Transaction item : transactions)
    	{
    		if(item.getTransactionType().getTransaction_type_name().equals(type_name)) 
    		{
    			typeTransactions.add(item);
    		}
    	}
    	
    	return typeTransactions;
		
	}
	
	public List<Transaction> getTransactionsByStatusName(String status_name)
	{
		
    	List<Transaction> statusTransactions = new ArrayList<Transaction>();
    	
    	List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
    	
    	for(Transaction item : transactions)
    	{
    		if(item.getTransactionStatus().getTransaction_status_name().equals(status_name)) 
    		{
    			statusTransactions.add(item);
    		}
    	}
    	
    	return statusTransactions;
		
	}

	public Transaction getTransactionByPaymentId(Integer payment_id)
	{
		
    	Transaction paymentTransaction = new Transaction();
    	
    	List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
    	
    	for(Transaction item : transactions)
    	{
    		if(item.getPayment_id().equals(payment_id)) 
    		{
    	    	paymentTransaction = item;
    		}
    	}
    	
    	return paymentTransaction;
	}
	
	public List<Transaction> getTransactionsBySenderId(Integer sender_id)
	{
		
    	List<Transaction> senderTransactions = new ArrayList<Transaction>();
    	
    	List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
    	
    	for(Transaction item : transactions)
    	{
    		if(item.getBank_account_sender_id().equals(sender_id)) 
    		{
    			senderTransactions.add(item);
    		}
    	}
    	
    	return senderTransactions;
		
	}
	
	public List<Transaction> getTransactionsByReceiverId(Integer receiver_id)
	{
		
    	List<Transaction> receiverTransactions = new ArrayList<Transaction>();
    	
    	List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
    	
    	for(Transaction item : transactions)
    	{
    		if(item.getBank_account_receiver_id().equals(receiver_id)) 
    		{
    			receiverTransactions.add(item);
    		}
    	}
    	
    	return receiverTransactions;
		
	}
	
	public List<Transaction> getTransactionsByDate(Date date)
	{
		
    	List<Transaction> dateTransactions = new ArrayList<Transaction>();
    	
    	List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
    	
    	for(Transaction item : transactions)
    	{
    		if(item.getDate().equals(date)) 
    		{
    			dateTransactions.add(item);
    		}
    	}
    	
    	return dateTransactions;
		
	}
	
	public Transaction saveTransaction(Transaction transaction)
	{
		
		return transactionRepository.save(transaction);
		
	}

	public void deleteTransaction(Integer id)
	{
		
		transactionRepository.delete(id);
		
	}
    
}
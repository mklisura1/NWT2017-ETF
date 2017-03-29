package TransactionsService.Services;

import java.util.ArrayList;
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

	public List<Transaction> getTransactionsByUserId(Integer id)
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
		
	}
    
	public List<Transaction> getTransactionsByTypeId(Integer type_id)
	{
		
    	List<Transaction> typeTransactions = new ArrayList<Transaction>();
    	
    	List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
    	
    	for(Transaction item : transactions)
    	{
    		if(item.getTransactionType().getTransaction_type_id().equals(type_id)) 
    		{
    			typeTransactions.add(item);
    		}
    	}
    	
    	return typeTransactions;
		
	}
	
	public List<Transaction> getTransactionsByStatusId(Integer status_id)
	{
		
    	List<Transaction> statusTransactions = new ArrayList<Transaction>();
    	
    	List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
    	
    	for(Transaction item : transactions)
    	{
    		if(item.getTransactionStatus().getTransaction_status_id().equals(status_id)) 
    		{
    			statusTransactions.add(item);
    		}
    	}
    	
    	return statusTransactions;
		
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
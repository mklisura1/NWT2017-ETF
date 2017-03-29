package TransactionsService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TransactionsService.Models.TransactionStatus;
import TransactionsService.Repository.TransactionStatusRepository;

@Service
public class TransactionStatusServiceImpl implements TransactionStatusService 
{
	@Autowired
	private TransactionStatusRepository transactionStatusRepository;
	
	
	public List<TransactionStatus> listAllTransactionStatuses()
    {
    	
    	return (List<TransactionStatus>) transactionStatusRepository.findAll();
    	
    }

    public TransactionStatus getTransactionStatusById(Integer id)
    {
    	
    	return transactionStatusRepository.findOne(id);
    	
    }
    
    public TransactionStatus getTransactionStatusByName(String status_name)
    {
    	
    	TransactionStatus status = null;
    	
    	List<TransactionStatus> statuses = (List<TransactionStatus>) transactionStatusRepository.findAll();
    	
    	for(TransactionStatus item : statuses)
    	{
    		
    		if(item.getTransaction_status_name().equals(status_name))
			{
				
				status = item;
				break;
				
			}
    		
    	}
    	
    	return status;
    	
    }

    public TransactionStatus saveTransactionStatus(TransactionStatus transactionStatus)
    {
    	
    	return transactionStatusRepository.save(transactionStatus);
    	
    }
    
    public void updateTransactionStatus(TransactionStatus transactionStatus)
    {
    	
    	transactionStatusRepository.save(transactionStatus);
    	
    }

    public void deleteTransactionStatus(Integer id)
    {
    	
    	transactionStatusRepository.delete(id);
    	
    }
    
}
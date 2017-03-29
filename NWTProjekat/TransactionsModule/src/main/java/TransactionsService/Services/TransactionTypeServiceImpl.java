package TransactionsService.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TransactionsService.Models.TransactionType;
import TransactionsService.Repository.TransactionTypeRepository;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService 
{
	@Autowired
	private TransactionTypeRepository transactionTypeRepository;

    public List<TransactionType> listAllTransactionTypes() 
    {
        return (List<TransactionType>) transactionTypeRepository.findAll();
    }

    public TransactionType getTransactionTypeById(Integer id) 
    {
        return transactionTypeRepository.findOne(id);
    }
    
    public TransactionType getTransactionTypeByName(String type_name)
    {
    	TransactionType type = null;
    	
    	List<TransactionType> types = (List<TransactionType>) transactionTypeRepository.findAll();
    	
    	for(TransactionType item : types)
    	{
    		
    		if(item.getTransaction_type_name().equals(type_name))
    		{
    			type = item;
    			
    			break;
    		}
    		
    	}
    	
    	return type;
    	
    }

    public TransactionType saveTransactionType(TransactionType transactionType) 
    {
        return transactionTypeRepository.save(transactionType);
    }

    public void deleteTransactionType(Integer id) 
    {
    	transactionTypeRepository.delete(id);
    }
}
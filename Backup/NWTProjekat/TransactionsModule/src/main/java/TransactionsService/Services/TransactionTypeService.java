package TransactionsService.Services;

import java.util.List;

import TransactionsService.Models.TransactionType;

public interface TransactionTypeService 
{
    List<TransactionType> listAllTransactionTypes();

    TransactionType getTransactionTypeById(Integer id);
    
    TransactionType getTransactionTypeByName(String type_name);

    TransactionType saveTransactionType(TransactionType transaction_type);
    
    void updateTransactionType(TransactionType transaction_type);

    void deleteTransactionType(Integer id);
}
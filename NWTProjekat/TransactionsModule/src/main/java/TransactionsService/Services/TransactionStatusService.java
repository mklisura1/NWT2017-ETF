package TransactionsService.Services;

import java.util.List;

import TransactionsService.Models.TransactionStatus;

public interface TransactionStatusService {
	
	List<TransactionStatus> listAllTransactionStatuses();

	TransactionStatus getTransactionStatusById(Integer id);

	TransactionStatus getTransactionStatusByName(String status_name);

	TransactionStatus saveTransactionStatus(TransactionStatus transactionStatus);

	void updateTransactionStatus(TransactionStatus transactionStatus);

	void deleteTransactionStatus(Integer id);
}
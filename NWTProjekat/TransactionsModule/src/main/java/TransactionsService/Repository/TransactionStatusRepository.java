package TransactionsService.Repository;

 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import TransactionsService.Models.TransactionStatus;

@Component
public interface TransactionStatusRepository extends CrudRepository<TransactionStatus, Integer> {
    Page<TransactionStatus> findAll(Pageable pageable);
}
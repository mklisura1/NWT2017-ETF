package TransactionsService.Repository;
 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import TransactionsService.Models.TransactionType;

public interface TransactionTypeRepository extends CrudRepository<TransactionType, Integer> {
    Page<TransactionType> findAll(Pageable pageable);
    //AccountModel findOne(ID id);
}

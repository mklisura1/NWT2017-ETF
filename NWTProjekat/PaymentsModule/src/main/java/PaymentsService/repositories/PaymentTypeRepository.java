package PaymentsService.repositories;

import PaymentsService.models.PaymentTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Hare on 17.03.2017..
 */
@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentTypeModel, Long> {
    //Page<PaymentTypeModel> findAll(Pageable pageable);
    //PaymentTypeModel findOne(ID id);
}

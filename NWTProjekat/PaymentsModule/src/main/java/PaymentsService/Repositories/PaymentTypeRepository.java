package PaymentsService.Repositories;

import PaymentsService.Models.PaymentTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Hare on 17.03.2017..
 */
@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentTypeModel, Long> {
    //Page<PaymentTypeModel> findAll(Pageable pageable);
    //PaymentTypeModel findOne(ID id);

    PaymentTypeModel findByPaymentTypeName(String status);
}

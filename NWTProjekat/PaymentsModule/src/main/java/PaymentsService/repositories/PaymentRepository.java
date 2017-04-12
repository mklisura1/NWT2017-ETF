package PaymentsService.repositories;

import PaymentsService.models.PaymentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Hare on 21.03.2017..
 */
@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {

    @Query("select p from #{#entityName} p where p.amount >= ?1")
    List<PaymentModel> findByAmountGTE(Double amountGTE);

    @Query("select p from #{#entityName} p where p.amount <= ?1")
    List<PaymentModel> findByAmountLTE(Double amountLTE);

    @Query("select p from #{#entityName} p where p.amount >= ?1 and p.amount <= ?2")
    List<PaymentModel> findByAmount(Double amountGTE, Double amountLTE);

  //  @Query("select * from #{#entityName} p where p.user_id = ?1")
//    List<PaymentModel> findAllUserPayments(Integer userId);

    Page<PaymentModel> findByUserId(Pageable pageable, Integer userId);


//    @Query("select p from #{#entityName} p where p.payment_type = ?1")
//    List<PaymentModel> findPaymentsByType(String paymentType);

}

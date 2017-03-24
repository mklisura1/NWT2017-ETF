package com.nwtprojekat.repositories;

import com.nwtprojekat.models.PaymentTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Hare on 17.03.2017..
 */
@Transactional
public interface PaymentTypeRepository extends JpaRepository<PaymentTypeModel, Long> {
    //Page<PaymentTypeModel> findAll(Pageable pageable);
    //PaymentTypeModel findOne(ID id);
}

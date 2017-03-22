package com.nwtprojekat.repositories;

import com.nwtprojekat.models.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Hare on 21.03.2017..
 */
@Transactional
public interface PaymentRepository extends JpaRepository<PaymentModel, Long> {
}

package com.nwtprojekat.services;

import com.nwtprojekat.models.PaymentModel;
import com.nwtprojekat.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Hare on 21.03.2017..
 */
@Service
@Repository
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @PostConstruct
    @Transactional
    public void populate() {
        PaymentModel p = new PaymentModel(1.023);
        p.setAmount(12.02);
        paymentRepository.saveAndFlush(p);
    }

    @Transactional(readOnly = true)
    public List<PaymentModel> getAll(){
        return paymentRepository.findAll();
    }

//    @SuppressWarnings("AssignmentToMethodParameter")
    @Transactional
    public PaymentModel saveAndFlush(PaymentModel p){
        if(p != null)
            p = paymentRepository.saveAndFlush(p);

        return p;
    }

    @Transactional
    public void delete(long id){
        paymentRepository.delete(id);
    }
}

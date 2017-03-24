package com.nwtprojekat.services;

import com.nwtprojekat.models.PaymentTypeModel;
import com.nwtprojekat.repositories.PaymentTypeRepository;
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
public class PaymentTypeService {
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @PostConstruct
    @Transactional
    public void populate() {
        PaymentTypeModel p = new PaymentTypeModel("DomaciNalog");
        paymentTypeRepository.saveAndFlush(p);
        p = new PaymentTypeModel("InostraniNalog");
        paymentTypeRepository.saveAndFlush(p);
        p = new PaymentTypeModel("InterniTransfer");
        paymentTypeRepository.saveAndFlush(p);
    }

    @Transactional(readOnly = true)
    public List<PaymentTypeModel> getAll(){
        return paymentTypeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PaymentTypeModel findOne(long id){
        return paymentTypeRepository.findOne(id);
    }

//    @SuppressWarnings("AssignmentToMethodParameter")
    @Transactional
    public PaymentTypeModel saveAndFlush(PaymentTypeModel p){
        if(p != null)
            p = paymentTypeRepository.saveAndFlush(p);

        return p;
    }
    @Transactional
    public PaymentTypeModel updatePayment(PaymentTypeModel p){
        if(p!=null)
            p = paymentTypeRepository.save(p);

        return p;
    }

    @Transactional
    public void delete(long id){
        paymentTypeRepository.delete(id);
    }
}

package PaymentsService.Services;

import PaymentsService.Models.PaymentTypeModel;
import PaymentsService.Repositories.PaymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Created by Hare on 21.03.2017..
 */
@Service
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
    public Page<PaymentTypeModel> getAll(Pageable pageable){
        return paymentTypeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public PaymentTypeModel findOne(long id){
        return paymentTypeRepository.findOne(id);
    }

//    @SuppressWarnings("AssignmentToMethodParameter")
    @Transactional
    public PaymentTypeModel insertPaymentType(PaymentTypeModel p){
        if(p != null)
            p = paymentTypeRepository.saveAndFlush(p);

        return p;
    }
    @Transactional
    public PaymentTypeModel updatePaymentType(PaymentTypeModel p){
        if(p!=null)
            p = paymentTypeRepository.save(p);

        return p;
    }

    @Transactional
    public void deletePaymentType(long id){
        paymentTypeRepository.delete(id);
    }
}

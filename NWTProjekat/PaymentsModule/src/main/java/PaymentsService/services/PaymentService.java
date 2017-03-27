package PaymentsService.services;

import PaymentsService.models.PaymentModel;
import PaymentsService.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
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
        PaymentModel p = new PaymentModel();
        p.setAmount(12.02);
        p.setDate(new Date());
        p.setPurpose("Probni nalog");
        p.setReceiverBankAccNumber("123456");
        p.setReceiverName("Haris Spahic");
        p.setSenderBankAccNumber("654321");
        p.setSenderName("Testni racun");
        p.setType("1");
        p.setTypeDescription("Tekuci racun");
        paymentRepository.saveAndFlush(p);

        p = new PaymentModel();
        p.setAmount(4.02);
        p.setDate(new Date());
        p.setPurpose("Probni nalog");
        p.setReceiverBankAccNumber("123456");
        p.setReceiverName("Haris Spahic");
        p.setSenderBankAccNumber("654321");
        p.setSenderName("Testni racun");
        p.setType("1");
        p.setTypeDescription("Tekuci racun");
        paymentRepository.saveAndFlush(p);
    }

    @Transactional(readOnly = true)
    public List<PaymentModel> getAll(){
        return paymentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PaymentModel findOne(long id){
        return paymentRepository.findOne(id);
    }

    @Transactional
    public PaymentModel saveAndFlush(PaymentModel p){
        if(p != null)
            p = paymentRepository.saveAndFlush(p);

        return p;
    }
    @Transactional
    public PaymentModel updatePayment(PaymentModel p){
        if(p!=null)
            p = paymentRepository.save(p);

        return p;
    }

    @Transactional
    public void delete(long id){
        paymentRepository.delete(id);
    }

    @Transactional
    public List<PaymentModel> getPaymentsByAmount(Double amountGTE, Double amountLTE){
        if(amountGTE != null && amountLTE != null)
            return paymentRepository.findByAmount(amountGTE,amountLTE);
        else if(amountGTE != null)
            return paymentRepository.findByAmountGTE(amountGTE);
        else
            return paymentRepository.findByAmountLTE(amountLTE);
    }

//    @Transactional
//    public List<PaymentModel> getPaymentsByType(String paymentType){
//        //not finished yet
//        return paymentRepository.findPaymentsByType(paymentType);
//    }

}

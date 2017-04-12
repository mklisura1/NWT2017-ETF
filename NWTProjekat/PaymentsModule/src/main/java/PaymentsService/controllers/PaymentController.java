package PaymentsService.controllers;

import PaymentsService.models.PaymentModel;
import PaymentsService.services.PaymentService;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hare on 21.03.2017..
 */
@RestController
@RequestMapping(value = "/api/payments")
public class PaymentController {
    static final org.apache.log4j.Logger logger = LogManager.getLogger(PaymentController.class.getName());

    @Autowired
    private PaymentService paymentService;


    @RequestMapping(method = RequestMethod.GET, produces="application/json")
    public Page<PaymentModel> getAllPayments(Pageable pageable,
                                             @RequestParam(required = false) Double amountGTE,
                                             @RequestParam(required = false) Double amountLTE,
                                             @RequestParam(required = false) String paymentType,
                                             @RequestParam(required = false) Integer userId) {

        logger.info("GET PAYMENTS");
        if(userId != null)
            return paymentService.getAllPaymentsForUser(pageable, userId);
        return paymentService.getAllPayments(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
    public PaymentModel getPaymentById(@PathVariable int id) {
        logger.info("GET PAYMENT BY ID: " + id);
        return paymentService.getPaymentById(id);
    }


    @RequestMapping(method = RequestMethod.POST, produces="application/json")
    public Object insertPayment(@RequestBody PaymentModel payment) {
        logger.info("INSERT TO PAYMENTS");
        PaymentModel p = new PaymentModel(payment);
        try {
            p = paymentService.insertPayment(p);

        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }

        return p;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces="application/json")
    public Object updatePayment(@PathVariable long id, @RequestBody PaymentModel payment) {
        logger.info("INSERT TO PAYMENTS");
        PaymentModel p = new PaymentModel(payment);
        p.setId(id);
        try {
            p = paymentService.updatePayment(p);
        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }
        return p;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces="application/json")
    public String deletePaymentById(@PathVariable long id) {
        logger.info("DELETE PAYMENT BY ID: " + id);

        try {
            paymentService.deletePayment(id);
        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }

        return "Payment successfully deleted!";

    }

}

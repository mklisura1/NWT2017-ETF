package PaymentsService.Controllers;

import PaymentsService.Models.PaymentTypeModel;
import PaymentsService.Services.PaymentTypeService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hare on 17.03.2017..
 */

@RestController
@RequestMapping(value = "/api/paymentTypes", produces="application/json")

public class PaymentTypeController {

    @Autowired
    private PaymentTypeService paymentTypeService;
    static final Logger logger = LogManager.getLogger(PaymentTypeController.class.getName());


    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public PaymentTypeModel getPaymentTypeById(@PathVariable("id") long id) {
        logger.info("GET PaymentType BY ID: " + id);
        return paymentTypeService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<PaymentTypeModel> getAllPaymentTypes(Pageable pageable) {
        logger.info("GET ALL PaymentTypes");
        return paymentTypeService.getAll(pageable);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object insertPaymentType(@RequestBody  PaymentTypeModel paymentTypeRequest){
        logger.info("INSERT TO PaymentTypes");
        PaymentTypeModel paymentType = new PaymentTypeModel(paymentTypeRequest);
        try{
            paymentType = paymentTypeService.insertPaymentType(paymentType);
        } catch (Exception e){
            logger.error(e);
            return e.getMessage();
        }

        return paymentType;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object updatePaymentType(@PathVariable long id, @RequestBody PaymentTypeModel payment){
        logger.info("INSERT TO PAYMENTS");
        PaymentTypeModel paymentType = new PaymentTypeModel(payment);
        try{
            paymentType = paymentTypeService.updatePaymentType(paymentType);
        } catch (Exception e){
            logger.error(e);
            return e.getMessage();
        }

        return paymentType;
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.DELETE)
    public String deletePaymentTypeById(@PathVariable long id) {
        logger.info("DELETE PAYMENT BY ID: " + id);
        try{
            paymentTypeService.deletePaymentType(id);
        } catch (Exception e){
            logger.error(e);
            return e.getMessage();
        }

        return "Payment successfully deleted!";

    }
}

package com.nwtprojekat.controllers;

import com.nwtprojekat.models.PaymentTypeModel;
import com.nwtprojekat.services.PaymentTypeService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hare on 17.03.2017..
 */

@RestController
@RequestMapping(value = "/paymentTypes")

public class PaymentTypeController {

    @Autowired
    private PaymentTypeService paymentTypeService;
    static final Logger logger = LogManager.getLogger(PaymentTypeController.class.getName());


    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public PaymentTypeModel getPaymentTypeById(@PathVariable("id") long id) {
        logger.info(String.format("GET PaymentType BY ID: " + id));
        PaymentTypeModel paymentType = paymentTypeService.findOne(id);
        return paymentType;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PaymentTypeModel> getAll(Pageable pageable) {
        logger.info(String.format("GET ALL PaymentTypes"));
        List<PaymentTypeModel> paymentTypes = paymentTypeService.getAll();
        return paymentTypes;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertAccount(@RequestBody  PaymentTypeModel paymentTypeRequest){
        logger.info(String.format("INSERT TO PaymentTypes"));
        PaymentTypeModel paymentType = new PaymentTypeModel(paymentTypeRequest);
        logger.info(paymentType);
        try{
            paymentTypeService.saveAndFlush(paymentType);
        } catch (Exception e){
            logger.error(e);
            return e.getMessage();
        }

        return "creation successful: " + String.valueOf(paymentType.getId());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updatePaymentType(@PathVariable long id, @RequestBody PaymentTypeModel payment){
        logger.info(String.format("INSERT TO PAYMENTS"));
        PaymentTypeModel p = new PaymentTypeModel(payment);
        try{
            paymentTypeService.updatePayment(p);
        } catch (Exception e){
            logger.error(e);
            return e.getMessage();
        }

        return "update successful: " + String.valueOf(p.getId());
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.DELETE)
    public String deletePaymentTypeById(@PathVariable long id) {
        logger.info(String.format("DELETE PAYMENT BY ID: " + id));
        try{
            paymentTypeService.delete(id);
        } catch (Exception e){
            logger.error(e);
            return e.getMessage();
        }

        return "Payment successfully deleted!";

    }
}

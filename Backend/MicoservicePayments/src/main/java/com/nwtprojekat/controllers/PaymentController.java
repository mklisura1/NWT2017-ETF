package com.nwtprojekat.controllers;

import com.nwtprojekat.models.PaymentModel;
import com.nwtprojekat.services.PaymentService;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hare on 21.03.2017..
 */
@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    static final org.apache.log4j.Logger logger = LogManager.getLogger(PaymentController.class.getName());


    @RequestMapping(method = RequestMethod.GET)
    public List<PaymentModel> getAllPayments(Pageable pageable) {
        logger.info(String.format("GET ALL PAYMENTS"));
        List<PaymentModel> payments = paymentService.getAll();
        return payments;
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public PaymentModel getPaymentById(@PathVariable int id) {
        logger.info(String.format("GET PAYMENT BY ID: " + id));

        PaymentModel p = paymentService.findOne(id);
        return p;
    }



    @RequestMapping(method = RequestMethod.POST)
    public String insertPayment(@RequestBody PaymentModel payment){
        logger.info(String.format("INSERT TO PAYMENTS"));
        PaymentModel p = new PaymentModel(payment);
        try{
            paymentService.saveAndFlush(p);
        } catch (Exception e){
            logger.error(e);
            return e.getMessage();
        }

        return String.format("Payment successfully inserted" + p.getId());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updatePayment(@PathVariable long id, @RequestBody PaymentModel payment){
        logger.info(String.format("INSERT TO PAYMENTS"));
        PaymentModel p = new PaymentModel(payment);
        p.setId(id);
        try{
            paymentService.updatePayment(p);
        } catch (Exception e){
            logger.error(e);
            return e.getMessage();
        }

        return "update successful: " + String.valueOf(p.getId());
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.DELETE)
    public String deletePaymentById(@PathVariable long id) {
        logger.info(String.format("DELETE PAYMENT BY ID: " + id));

        try{
            paymentService.delete(id);
        } catch (Exception e){
            logger.error(e);
            return e.getMessage();
        }

        return "Payment successfully deleted!";

    }

}

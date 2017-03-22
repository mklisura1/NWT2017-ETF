package com.nwtprojekat.controllers;

import com.nwtprojekat.models.PaymentModel;
import com.nwtprojekat.services.PaymentService;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Hare on 21.03.2017..
 */
@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    static final org.apache.log4j.Logger logger = LogManager.getLogger(AccountController.class.getName());

    @RequestMapping(method = RequestMethod.GET)
    public List<PaymentModel> getAllPayments(Pageable pageable) {
        logger.info(String.format("GET ALL PAYMENTS"));
        List<PaymentModel> payments = paymentService.getAll();
        return payments;
    }

}

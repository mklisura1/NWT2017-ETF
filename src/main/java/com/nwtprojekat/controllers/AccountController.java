package com.nwtprojekat.controllers;

import com.nwtprojekat.models.AccountModel;
import com.nwtprojekat.repository.AccountRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Hare on 17.03.2017..
 */

@RestController
@RequestMapping(value = "/accounts")

public class AccountController {

    @Autowired
    private AccountRepository repo;
    static final Logger logger = LogManager.getLogger(AccountController.class.getName());

    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public String insertTestAccounts() {
        logger.info(String.format("Insert test accounts"));
        AccountModel a1 = new AccountModel("Tekuci racun");
        AccountModel a2 = new AccountModel("Devizni racun");
        AccountModel a3 = new AccountModel("Stedni racun");
        try{
            repo.save(a1);
            repo.save(a2);
            repo.save(a3);
        } catch (Exception e){
            logger.error(e);
            return e.getMessage();
        }
        return "Testni racuni dodani! Dobaviti sve racune preko .../accounts";
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public AccountModel getById(@PathVariable("id") int id) {
        logger.info(String.format("GET ACCOUNT BY ID: " + id));

        AccountModel account = repo.findOne(id);
        return account;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<AccountModel> getAll(Pageable pageable) {
        logger.info(String.format("GET ALL ACCOUNTS"));
        Page<AccountModel> accounts = repo.findAll(pageable);

        return accounts;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertAccount(@RequestParam  String name){
        logger.info(String.format("INSERT TO ACCOUNTS"));
        AccountModel acccount = new AccountModel(name);
        logger.info(acccount);
        try{
            repo.save(acccount);
        } catch (Exception e){
            logger.error(e);
            return e.getMessage();
        }

        return "creation successful: " + String.valueOf(acccount.getId());
    }
}

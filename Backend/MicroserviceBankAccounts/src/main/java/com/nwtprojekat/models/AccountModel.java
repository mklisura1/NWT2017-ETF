package com.nwtprojekat.models;

import javax.persistence.*;
import java.util.Random;

/**
 * Created by Hare on 17.03.2017..
 */

@Entity
@Table(name = "accounts")
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public int getId() {
        return id;
    }

    @Column(name = "accountName", nullable = false)
    private String accountName;
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Column(name = "balance", nullable = false)
    private Double balance;
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountModel() {}
    public AccountModel(String accountName) {
        this.balance = new Random().nextDouble();
        this.accountName = accountName;
    }






}

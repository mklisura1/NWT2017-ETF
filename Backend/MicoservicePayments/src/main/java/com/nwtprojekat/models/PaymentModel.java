package com.nwtprojekat.models;

import javax.persistence.*;

/**
 * Created by Hare on 21.03.2017..
 */
@Entity
@Table(name = "payments")
public class PaymentModel {

    @Id
    @GeneratedValue
    @Column(name = "paymentId", unique = true, nullable = false)
    private int Id;

    @Column(name = "amount", nullable = false)
    private double amount;

    public PaymentModel() {
    }

    public PaymentModel(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

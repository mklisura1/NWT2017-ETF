package UsersService.Templates;


import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.util.Date;

public class PaymentModel {

    private long id;

    
    private double amount;
    
    private int userId;
    
    private Date date;
    
    private String senderName;
    
    private String senderBankAccNumber;
    
    private String receiverName;
    
    private String receiverBankAccNumber;
    
    private String purpose;

    private Object type;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    private String status;

    private String typeDescription;

    public PaymentModel() {
    }

    public PaymentModel(double amount) {
        this.amount = amount;
    }

    public PaymentModel(PaymentModel payment) {
        this.amount = payment.amount;
        this.date = payment.date;
        this.userId = payment.userId;
        this.senderName = payment.senderName;
        this.senderBankAccNumber = payment.senderBankAccNumber;
        this.receiverName = payment.receiverName;
        this.receiverBankAccNumber = payment.receiverBankAccNumber;
        this.purpose = payment.purpose;
        Object pType = new Object();

        this.type = payment.type;
        this.status = payment.status;
        this.typeDescription = payment.typeDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderBankAccNumber() {
        return senderBankAccNumber;
    }

    public void setSenderBankAccNumber(String senderBankAccNumber) {
        this.senderBankAccNumber = senderBankAccNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverBankAccNumber() {
        return receiverBankAccNumber;
    }

    public void setReceiverBankAccNumber(String receiverBankAccNumber) {
        this.receiverBankAccNumber = receiverBankAccNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
/*
    public String getType() {
        return type.getPaymentTypeName();
    }

    public void setType(PaymentTypeModel type) {
        this.type = type;
    }
*/
    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

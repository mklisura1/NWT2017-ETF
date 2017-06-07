package nwt.ebanking.model;


import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    private String type;
    
    private String status;
    
    private String typeDescription;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public PaymentModel(long id, double amount, int userId, Date date, String senderName, String senderBankAccNumber,
			String receiverName, String receiverBankAccNumber, String purpose, String type, String status,
			String typeDescription) {
		super();
		this.id = id;
		this.amount = amount;
		this.userId = userId;
		this.date = date;
		this.senderName = senderName;
		this.senderBankAccNumber = senderBankAccNumber;
		this.receiverName = receiverName;
		this.receiverBankAccNumber = receiverBankAccNumber;
		this.purpose = purpose;
		this.type = type;
		this.status = status;
		this.typeDescription = typeDescription;
	}

	public PaymentModel() {
		super();
	}

/*
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
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
blic String getTypeDescription() {
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
    */
    
    
    
}

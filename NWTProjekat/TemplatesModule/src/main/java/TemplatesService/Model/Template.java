package TemplatesService.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="template")
public class Template {
	
	@Id
	@GeneratedValue
	private Integer template_id;
	private String template_name;

	public String getTemplate_name() {
		return template_name;
	}

	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	private Integer userId;
	private String sender_name;	
	private String receiver_name;
	private String sender_bank_acc_number;	
	private String receiver_bank_acc_number;
	private String payment_purpose;
	private Integer payment_type_id;
	private String payment_type;
	private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    //Constructors
	public Template() {
		super();
	}
	
	public Template(Integer template_id, String template_name, Integer userId, String sender_name, String receiver_name,
			String sender_bank_acc_number, String receiver_bank_acc_number, String payment_purpose,
			Integer payment_type_id, String payment_type, Double amount) {
		super();
		this.template_id = template_id;
		this.template_name = template_name;
		this.userId = userId;
		this.sender_name = sender_name;
		this.receiver_name = receiver_name;
		this.sender_bank_acc_number = sender_bank_acc_number;
		this.receiver_bank_acc_number = receiver_bank_acc_number;
		this.payment_purpose = payment_purpose;
		this.payment_type_id = payment_type_id;
		this.payment_type = payment_type;
		this.amount = amount;
	}

	//Getters & Setters
	public Integer getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(Integer template_id) {
		this.template_id = template_id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public String getReceiver_name() {
		return receiver_name;
	}
	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getSender_bank_acc_number() {
		return sender_bank_acc_number;
	}
	public void setSender_bank_acc_number(String sender_bank_acc_number) {
		this.sender_bank_acc_number = sender_bank_acc_number;
	}
	public String getReceiver_bank_acc_number() {
		return receiver_bank_acc_number;
	}
	public void setReceiver_bank_acc_number(String receiver_bank_acc_number) {
		this.receiver_bank_acc_number = receiver_bank_acc_number;
	}
	public String getPayment_purpose() {
		return payment_purpose;
	}
	public void setPayment_purpose(String payment_purpose) {
		this.payment_purpose = payment_purpose;
	}
	public Integer getPayment_type_id() {
		return payment_type_id;
	}
	public void setPayment_type_id(Integer payment_type_id) {
		this.payment_type_id = payment_type_id;
	}	
}

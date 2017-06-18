package nwt.ebanking.model;

public class Template {

	private Integer template_id;
	private Integer user_id;
	private String sender_name;	
	private String receiver_name;
	private String sender_bank_acc_number;	
	private String receiver_bank_acc_number;
	private String payment_purpose;
	private Integer payment_type_id;
	
	//Constructors
	public Template() {
		super();
	}
	
	public Template(Integer template_id, Integer user_id, String sender_name, String receiver_name,
			String sender_bank_acc_number, String receiver_bank_acc_number, String payment_purpose,
			Integer payment_type_id) {
		super();
		this.template_id = template_id;
		this.user_id = user_id;
		this.sender_name = sender_name;
		this.receiver_name = receiver_name;
		this.sender_bank_acc_number = sender_bank_acc_number;
		this.receiver_bank_acc_number = receiver_bank_acc_number;
		this.payment_purpose = payment_purpose;
		this.payment_type_id = payment_type_id;
	}

	//Getters & Setters
	public Integer getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(Integer template_id) {
		this.template_id = template_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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

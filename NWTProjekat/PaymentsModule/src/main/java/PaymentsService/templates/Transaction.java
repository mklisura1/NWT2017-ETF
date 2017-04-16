package PaymentsService.templates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
	

	private Integer transaction_id;
	private Object transactionType;
	private Object transactionStatus;
	private Integer bank_account_sender_id;
	private Integer bank_account_receiver_id;
	private Integer user_id;
	private Integer payment_id;
	private Date date;
	
	public Transaction()
	{
		super();
	}

	public Transaction(Integer transaction_id, Object transactionType, Object transactionStatus,
			Integer bank_account_sender_id, Integer bank_account_receiver_id, Integer user_id, Integer payment_id,
			Date date) {
		super();
		this.transaction_id = transaction_id;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.bank_account_sender_id = bank_account_sender_id;
		this.bank_account_receiver_id = bank_account_receiver_id;
		this.user_id = user_id;
		this.payment_id = payment_id;
		this.date = date;
	}

	public Integer getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Object getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Object transactionType) {
		this.transactionType = transactionType;
	}

	public Object getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(Object transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Integer getBank_account_sender_id() {
		return bank_account_sender_id;
	}

	public void setBank_account_sender_id(Integer bank_account_sender_id) {
		this.bank_account_sender_id = bank_account_sender_id;
	}

	public Integer getBank_account_receiver_id() {
		return bank_account_receiver_id;
	}

	public void setBank_account_receiver_id(Integer bank_account_receiver_id) {
		this.bank_account_receiver_id = bank_account_receiver_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Integer payment_id) {
		this.payment_id = payment_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

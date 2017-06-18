package TransactionsService.Models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@GeneratedValue
	private Integer transaction_id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="transaction_type_id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private TransactionType transactionType;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="transaction_status_id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private TransactionStatus transactionStatus;
	private Integer bank_account_sender_id;
	private Integer bank_account_receiver_id;
	private Integer sender_id;
	private Integer receiver_id;
	private Integer payment_id;
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date date;
	
	public Transaction()
	{
		super();
	}

	public Transaction(Integer transaction_id, TransactionType transactionType, TransactionStatus transactionStatus,
			Integer bank_account_sender_id, Integer bank_account_receiver_id, Integer sender_id, Integer receiver_id, Integer payment_id,
			Date date) {
		super();
		this.transaction_id = transaction_id;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.bank_account_sender_id = bank_account_sender_id;
		this.bank_account_receiver_id = bank_account_receiver_id;
		this.sender_id = sender_id;
		this.receiver_id = receiver_id;
		this.payment_id = payment_id;
		this.date = date;
	}
	
	public Integer getSender_id() {
		return sender_id;
	}

	public void setSender_id(Integer sender_id) {
		this.sender_id = sender_id;
	}

	public Integer getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(Integer receiver_id) {
		this.receiver_id = receiver_id;
	}
	
	public Integer getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
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

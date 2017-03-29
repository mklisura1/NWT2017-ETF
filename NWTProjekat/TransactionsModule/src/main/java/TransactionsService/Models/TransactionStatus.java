package TransactionsService.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction_status")
public class TransactionStatus {
	
	@Id
	@GeneratedValue
	private Integer transaction_status_id;
	private String transaction_status_name;
	
	//Constructors
	public TransactionStatus() 
	{ 
		super();
	}

	public TransactionStatus(Integer transaction_status_id, String transaction_status_name) {
		super();
		this.transaction_status_id = transaction_status_id;
		this.transaction_status_name = transaction_status_name;
	}

	public Integer getTransaction_status_id() {
		return transaction_status_id;
	}

	public void setTransaction_status_id(Integer transaction_status_id) {
		this.transaction_status_id = transaction_status_id;
	}

	public String getTransaction_status_name() {
		return transaction_status_name;
	}

	public void setTransaction_status_name(String transaction_status_name) {
		this.transaction_status_name = transaction_status_name;
	}
	
}

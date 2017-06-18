package TransactionsService.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction_type")
public class TransactionType {
	
	@Id
	@GeneratedValue
	private Integer transaction_type_id;
	private String transaction_type_name;
	
	//Constructors
	public TransactionType() {
		super();
	}

	public TransactionType(Integer transaction_type_id, String transaction_type_name) {
		super();
		this.transaction_type_id = transaction_type_id;
		this.transaction_type_name = transaction_type_name;
	}

	public Integer getTransaction_type_id() {
		return transaction_type_id;
	}

	public void setTransaction_type_id(Integer transaction_type_id) {
		this.transaction_type_id = transaction_type_id;
	}

	public String getTransaction_type_name() {
		return transaction_type_name;
	}

	public void setTransaction_type_name(String transaction_type_name) {
		this.transaction_type_name = transaction_type_name;
	}

}

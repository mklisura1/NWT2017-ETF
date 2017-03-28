package Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BankAccountTypes")
public class BankAccountType {

	@Id
	@GeneratedValue
	private int bank_account_type_id;

	@Column(name = "Name")
	private String bank_account_type_name;

	public int getBank_account_type() {
		return bank_account_type_id;
	}

	public String getBank_account_type_name() {
		return bank_account_type_name;
	}

	public void setBank_account_name(String bank_account_name) {
		this.bank_account_type_name = bank_account_name;
	}
}

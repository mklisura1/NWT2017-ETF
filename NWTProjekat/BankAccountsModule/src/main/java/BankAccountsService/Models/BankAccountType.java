package BankAccountsService.Models;

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

	public BankAccountType() {
		super();
	}

	public BankAccountType(int bank_account_type_id, String bank_account_type_name) {
		super();
		this.bank_account_type_id = bank_account_type_id;
		this.bank_account_type_name = bank_account_type_name;
	}

	public int getBank_account_type_id() {
		return bank_account_type_id;
	}

	public void setBank_account_type_id(int bank_account_type_id) {
		this.bank_account_type_id = bank_account_type_id;
	}

	public String getBank_account_type_name() {
		return bank_account_type_name;
	}

	public void setBank_account_type_name(String bank_account_type_name) {
		this.bank_account_type_name = bank_account_type_name;
	}
}

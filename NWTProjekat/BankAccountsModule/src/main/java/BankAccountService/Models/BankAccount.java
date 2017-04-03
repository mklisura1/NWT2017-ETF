package BankAccountService.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="BankAccounts")
public class BankAccount {
		
	@Id
	@GeneratedValue
	private int bank_account_id;
	
	@Column(name="Name")
	private String bank_account_name;
	
	@Column(name="UserId")
	private int user;

	@OneToOne(fetch=FetchType.EAGER)
	@Column(name="AccountType")
	@JoinColumn(name="bank_account_type_id")
	@Cascade(CascadeType.SAVE_UPDATE)
	private int bank_account_type;
	
	@Column(name="CreditAmount")
	private int credit_amount;
	
	@Column(name="AccountNumber")
	private String bank_account_number;
	
	public BankAccount()
	{
		super();
	}
		
	public BankAccount(int bank_account_id, String bank_account_name, int user, int bank_account_type,
			int credit_amount, String bank_account_number) {
		super();
		this.bank_account_id = bank_account_id;
		this.bank_account_name = bank_account_name;
		this.user = user;
		this.bank_account_type = bank_account_type;
		this.credit_amount = credit_amount;
		this.bank_account_number = bank_account_number;
	}
	
	public int GetBank_account_id() {
		return bank_account_id;
	}
	public void SetBank_account_id(int bank_account_id) {
		this.bank_account_id = bank_account_id;
	}
	public String GetBank_account_name() {
		return bank_account_name;
	}
	public void SetBank_account_name(String bank_account_name) {
		this.bank_account_name = bank_account_name;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getBank_account_type() {
		return bank_account_type;
	}
	public void setBank_account_type(int bank_account_type) {
		this.bank_account_type = bank_account_type;
	}
	public int getCredit_amount() {
		return credit_amount;
	}
	public void setCredit_amount(int credit_amount) {
		this.credit_amount = credit_amount;
	}
	public String getBank_account_number() {
		return bank_account_number;
	}
	public void setBank_account_number(String bank_account_number) {
		this.bank_account_number = bank_account_number;
	}	
}

package Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import UsersService.Models.User;

@Entity
@Table(name="BankAccounts")
public class BankAccount {
	
	@Id
	@GeneratedValue
	private int bank_account_id;
	
	@Column(name="Name")
	private String bank_account_name;
	
	@ManyToOne
	@Column(name="UserId")
	private User user;
	
	@OneToOne
	@Column(name="AccountType")
	private int bank_account_type;
	
	@Column(name="CreditAmount")
	private int credit_amount;
	
	@Column(name="AcountNumber")
	private String bank_account_number;
	
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
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

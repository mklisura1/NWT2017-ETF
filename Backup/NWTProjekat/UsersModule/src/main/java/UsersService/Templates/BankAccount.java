package UsersService.Templates;

public class BankAccount {

	private int bank_account_id;
	
	private String bank_account_name;
	
	private int user;

	private Object bankAccountType;
	
	private int credit_amount;
	
	private String bank_account_number;
	
	public BankAccount()
	{
		super();
	}
	
	
	public BankAccount(int bank_account_id, String bank_account_name, int user, Object bankAccountType,
			int credit_amount, String bank_account_number) {
		super();
		this.bank_account_id = bank_account_id;
		this.bank_account_name = bank_account_name;
		this.user = user;
		this.bankAccountType = bankAccountType;
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
	
	public int getBank_account_id() {
		return bank_account_id;
	}


	public void setBank_account_id(int bank_account_id) {
		this.bank_account_id = bank_account_id;
	}


	public String getBank_account_name() {
		return bank_account_name;
	}


	public void setBank_account_name(String bank_account_name) {
		this.bank_account_name = bank_account_name;
	}


	public Object getBankAccountType() {
		return bankAccountType;
	}


	public void setBankAccountType(Object bankAccountType) {
		this.bankAccountType = bankAccountType;
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

package BankAccount.tests;

import java.awt.print.Printable;

import javax.security.auth.login.AccountException;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;

import BankAccountService.Interfaces.BankAccountRepository;
import BankAccountService.Models.BankAccount;
import BankAccountsService.BankAccountsApplication;

public class Test {

	@Autowired
	private BankAccountRepository bankAccountRepo;

	@org.junit.Test
	public void CreateAccount() {
		
		BankAccountsApplication application = new BankAccountsApplication();
		//application.main(null);

		try {
			BankAccount account = new BankAccount();
			account.SetBank_account_name("Test1");
			account.setBank_account_number("123");
			account.setBank_account_type(1);
			bankAccountRepo.save(account);

		} catch (Exception e) {
			String string = e.toString();
		}
	}
}

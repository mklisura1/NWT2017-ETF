package nwt.ebanking.controller;

import java.awt.image.BandCombineOp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.TypeFactory;

import nwt.ebanking.model.BankAccount;
import nwt.ebanking.model.PaymentModel;
import nwt.ebanking.model.Template;
import nwt.ebanking.model.Transaction;
import nwt.ebanking.model.User;

@Controller
@RequestMapping(value = "/users")
public class UsersController {
	
	
	
	/* 
	 * GET metoda za dobavljanje profila usera 
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView userProfile(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("userprofileview");
		
		System.out.println("1");
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token = (String) request.getSession().getAttribute("token");
		String token2 = "Bearer " + token;
		headers.add("X-Authorization", token2);
		headers.add("Content-Type", "application/json");
		
		System.out.println("2");
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		System.out.println("3");
		// send request and parse result
		ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1102/api/payments/1", HttpMethod.GET, entity, String.class);
		ResponseEntity<String> loginResponseUser = restTemplate.exchange("http://localhost:1105/api/user/" + user_id + "/account", HttpMethod.GET, entity, String.class);
		//String response = loginResponse.getBody().substring(13, loginResponse.getBody().indexOf("totalElements")-3);
		System.out.println("4");
		
		User user = null;
		
		try {
			PaymentModel pm =  new ObjectMapper().readValue(loginResponse.getBody(), PaymentModel.class);
			user = new ObjectMapper().readValue(loginResponseUser.getBody(), User.class);
			
			System.out.println("4.1" + pm.getAmount());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("5");
		
		modelAndView.addObject("user", user);
		/*
		modelAndView.addObject("userDetails", user.getUserDetails());
		modelAndView.addObject("friends_size", user.getFriends().size());
		
		List<GalleryPicture> pictures = new ArrayList<GalleryPicture>();
		for(Gallery item: user.getGalleries()) pictures.addAll(item.getGalleryPictures());
		modelAndView.addObject("pictures", pictures);
		modelAndView.addObject("photos_size", pictures.size());
		*/
		return modelAndView;
	}
	
	/* 
	 * GET metoda za dobavljanje bankovnih racuna usera 
	 */
	@RequestMapping(value = "/list/bankaccount", method = RequestMethod.GET)
	public ModelAndView userBankAccounts(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("userbankaccountlistview");
		
		User user = getUserFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		
		modelAndView.addObject("user", user);		
		List<BankAccount> bankAccounts = getBankAccountsFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		
		modelAndView.addObject("bankAccounts", bankAccounts);
		
		return modelAndView;
	}
	
	/* 
	 * GET metoda za dodavanje jednog bankovnog racuna useru 
	 */
	@RequestMapping(value = "/add/bankaccount", method = RequestMethod.GET)
	public ModelAndView userAddBankAccount(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("userbankaccountaddview");
		
		BankAccount bankAccount = new BankAccount();
		User user = getUserFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		modelAndView.addObject("bankAccount", bankAccount);
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	/* 
	 * POST metoda za dodavanje jednog bankovnog racuna useru 
	 */
	@RequestMapping(value = "/add/bankaccount", method = RequestMethod.POST)
	public ModelAndView userAddBankAccount(@ModelAttribute("bankAccount") BankAccount bankAccount, BindingResult result, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("userprofileview");
		
		int randomNum = ThreadLocalRandom.current().nextInt(5, 100 + 1);
		Date date = Calendar.getInstance().getTime();
		
		List<BankAccount> bankAccounts = getBankAccountsFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		BankAccount old_account = new BankAccount();
		
		for(BankAccount item: bankAccounts)
		{
			if(item.getBank_account_id() == 1) old_account = item;
		}
		
		bankAccount.setBank_account_id(randomNum);
		bankAccount.setUser(user_id);
		bankAccount.setBankAccountType(old_account.getBankAccountType());

		boolean zavrseno = setUserBankAccountToService(request, (String) request.getSession().getAttribute("token"), user_id, bankAccount);
		
		return new ModelAndView("redirect:/users/profile");
	}
	
	/* 
	 * GET metoda za brisanje jednog bank accounta useru 
	 */
	@RequestMapping(value = "/delete/bankaccount/{account_id}", method = RequestMethod.GET)
	public ModelAndView deleteBankAccount(@PathVariable("account_id") Integer account_id, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token = (String) request.getSession().getAttribute("token");
		String token2 = "Bearer " + token;
		headers.add("X-Authorization", token2);
		headers.add("Content-Type", "application/json");
		
		System.out.println("2");
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		System.out.println("3");
		// send request and parse result
		ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1101/api/accounts/"+ account_id + "", HttpMethod.DELETE, entity, String.class);
		System.out.println("4");
		
		
		return new ModelAndView("redirect:/users/profile");
	}

	/* 
	 * GET metoda za dobavljanje transakcija usera 
	 */
	@RequestMapping(value = "/list/transaction", method = RequestMethod.GET)
	public ModelAndView userTransactions(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("usertransactionlistview");
		
		User user = getUserFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		
		modelAndView.addObject("user", user);		
		List<Transaction> transactions = getUserTransactionFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		
		modelAndView.addObject("transactions", transactions);
		
		return modelAndView;
	}
	
	/* 
	 * GET metoda za dodavanje jedne transakcije useru 
	 */
	@RequestMapping(value = "/add/transaction", method = RequestMethod.GET)
	public ModelAndView userAddTransaction(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		User user = getUserFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		
		ModelAndView modelAndView = new ModelAndView("usertransactionaddview");
		
		List<PaymentModel> payments = getUserPaymentsFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("payments", payments);

		return modelAndView;
	}
	
	/* 
	 * POST metoda za dodavanje jedne transakcije useru 
	 */
	@RequestMapping(value = "/add/transaction/{payment_id}", method = RequestMethod.GET)
	public ModelAndView userAddTransaction(@PathVariable("payment_id") Integer payment_id,  HttpServletRequest request) {

		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		System.out.println("Usao sam u metodu za pretvaranje paymenta u transakciju");
		
		List<Transaction> transactions = getUserTransactionFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		List<PaymentModel> payments = getUserPaymentsFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		
		Transaction old_transaction = new Transaction();
		PaymentModel old_payment = new PaymentModel();
		PaymentModel payment = new PaymentModel();
		double payment_treba=0;
		
		for(PaymentModel item: payments)
		{
			if(item.getId()==1) old_payment = item;
		}
		
		for(Transaction item: transactions)
		{
			if(item.getTransaction_id().equals(1)) old_transaction = item;
		}
		
		System.out.println("Dobavljanje pejmenta po id");
		for(PaymentModel item: payments)
		{
			System.out.println("payment id: " + payment_id + " item: " + item.getId());
			if(payment_id.equals((int)item.getId())) {
				payment = item; payment_treba =item.getAmount();
				System.out.println("Ammount " + item.getAmount());
			}
		}
		
		Transaction transaction = new Transaction();
		
		int randomNum = ThreadLocalRandom.current().nextInt(5, 100 + 1);
		Date date = Calendar.getInstance().getTime();
		
		transaction.setTransaction_id(randomNum);
		//transaction.setBank_account_sender_id(Integer.parseInt(old_payment.getSenderBankAccNumber()));
		//transaction.setBank_account_receiver_id(Integer.parseInt(old_payment.getReceiverBankAccNumber()));
		transaction.setPayment_id(payment_id);
		transaction.setUser_id(user_id);
		transaction.setDate(date);
		transaction.setTransactionStatus(old_transaction.getTransactionStatus());
		transaction.setTransactionType(old_transaction.getTransactionType());
		
		//Dobavljanje usera koji imaju ove bankovne racune
		BankAccount sender_bank_account = getUserFromService(request, (String) request.getSession().getAttribute("token"), old_payment.getSenderBankAccNumber());
		BankAccount receiver_bank_account = getUserFromService(request, (String) request.getSession().getAttribute("token"), old_payment.getReceiverBankAccNumber());
		BankAccount sender_bank_account_temp = sender_bank_account;
		BankAccount receiver_bank_account_temp = receiver_bank_account;
		
		System.out.println("Payment ammount " + payment_treba);
		
		double sender_ammount = (sender_bank_account.getCredit_amount()-payment_treba);
		double receiver_ammount = (receiver_bank_account.getCredit_amount()+payment_treba);
		
		System.out.println("sender ammount " + sender_ammount);
		System.out.println("receiver ammount " + receiver_ammount);
		
		boolean delete_sender = deleteBankAccount(request, (String) request.getSession().getAttribute("token"), sender_bank_account);
		boolean delete_receiver = deleteBankAccount(request, (String) request.getSession().getAttribute("token"), receiver_bank_account);
		
		sender_bank_account_temp.setCredit_amount((int) sender_ammount);
		receiver_bank_account_temp.setCredit_amount((int) receiver_ammount);
		int randomNum2 = ThreadLocalRandom.current().nextInt(5, 100 + 1);
		int randomNum3 = ThreadLocalRandom.current().nextInt(5, 100 + 1);
		
		sender_bank_account_temp.setBank_account_id(randomNum2);
		receiver_bank_account_temp.setBank_account_id(randomNum2);
		
		boolean zavrseno1 = setUserBankAccountToService(request, (String) request.getSession().getAttribute("token"), user_id, sender_bank_account_temp);
		boolean zavrseno2 = setUserBankAccountToService(request, (String) request.getSession().getAttribute("token"), user_id, receiver_bank_account_temp);
		
		transaction.setBank_account_receiver_id(receiver_bank_account_temp.getBank_account_id());
		transaction.setBank_account_sender_id(sender_bank_account_temp.getBank_account_id());
		
		if(setUserTransactionToService(request, (String) request.getSession().getAttribute("token"), user_id, transaction))
			return new ModelAndView("redirect:/users/list/transaction");
		else return new ModelAndView("redirect:/users/add/transaction");
	}
	
	/* 
	 * GET metoda za brisanje jedne transakcije useru 
	 */
	@RequestMapping(value = "/delete/transaction/{transaction_id}", method = RequestMethod.GET)
	public ModelAndView deleteTransaction(@PathVariable("transaction_id") Integer transaction_id, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token = (String) request.getSession().getAttribute("token");
		String token2 = "Bearer " + token;
		headers.add("X-Authorization", token2);
		headers.add("Content-Type", "application/json");
		
		System.out.println("2");
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		System.out.println("3");
		// send request and parse result
		ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1104/api/transaction/"+ transaction_id + "", HttpMethod.DELETE, entity, String.class);
		System.out.println("4");
		
		
		return new ModelAndView("redirect:/users/profile");
	}
	
	/* 
	 * GET metoda za dodavanje jednog paymenta useru 
	 */
	@RequestMapping(value = "/add/payment", method = RequestMethod.GET)
	public ModelAndView userAddPayment(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("userpaymentaddview");
		
		User user = getUserFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		PaymentModel payment = new PaymentModel();
		
		modelAndView.addObject("payment", payment);
		modelAndView.addObject("user", user);
		
		
		return modelAndView;
	}
	
	/* 
	 * POST metoda za dodavanje jednog pajmenta useru 
	 */
	@RequestMapping(value = "/add/payment", method = RequestMethod.POST)
	public ModelAndView userAddPayment(@ModelAttribute("payment") PaymentModel payment, BindingResult result, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("userprofileview");
		
		int randomNum = ThreadLocalRandom.current().nextInt(5, 100 + 1);
		Date date = Calendar.getInstance().getTime();
		List<PaymentModel> payments = getUserPaymentsFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		PaymentModel old_payment = new PaymentModel();
		
		for(PaymentModel item: payments)
		{
			if(item.getId() == 1) old_payment = item;
		}
		
		String type = "1";
		
		payment.setId(randomNum);
		payment.setUserId(user_id);
		payment.setDate(date);
		payment.setType(type);
		payment.setStatus(old_payment.getStatus());

		boolean zavrseno = setUserPaymentToService(request, (String) request.getSession().getAttribute("token"), user_id, payment);
		
		return new ModelAndView("redirect:/users/profile");
	}
	
	/* 
	 * GET metoda za dobavljanje templejta usera 
	 */
	@RequestMapping(value = "/list/template", method = RequestMethod.GET)
	public ModelAndView userTemplates(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("usertemplatelistview");
		User user = getUserFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		List<Template> templates = getUserTemplatesFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		modelAndView.addObject("templates", templates);
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	/* 
	 * GET metoda za dodavanje jednog templejta useru 
	 */
	@RequestMapping(value = "/add/template", method = RequestMethod.GET)
	public ModelAndView userAddTemplate(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("usertemplateaddview");
		Template template = new Template();
		User user = getUserFromService(request, (String) request.getSession().getAttribute("token"), user_id);
		modelAndView.addObject("template", template);
		modelAndView.addObject("user", user);
		return modelAndView;
	}
	
	/* 
	 * POST metoda za dodavanje jednog templejta useru 
	 */
	@RequestMapping(value = "/add/template", method = RequestMethod.POST)
	public ModelAndView userAddTemplate(@ModelAttribute("template") Template template, BindingResult result, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("usertemplatelisteview");
		
		//Postavljanje vrijednosti za template koje nisu postavljene preko view-a
		int randomNum = ThreadLocalRandom.current().nextInt(5, 100 + 1);
		template.setTemplate_id(randomNum);
		template.setUser_id(user_id);
		template.setPayment_type_id(1);

		boolean zavrseno = setUserTemplatesToService(request, (String) request.getSession().getAttribute("token"), user_id, template);
		
		if(zavrseno) return modelAndView;
		else return new ModelAndView("redirect:/users/add/template");
	}
	
	/* 
	 * GET metoda za brisanje jednog template useru 
	 */
	@RequestMapping(value = "/delete/template/{template_id}", method = RequestMethod.GET)
	public ModelAndView deleteAddBankAccount(@PathVariable("template_id") Integer template_id, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token = (String) request.getSession().getAttribute("token");
		String token2 = "Bearer " + token;
		headers.add("X-Authorization", token2);
		headers.add("Content-Type", "application/json");
		
		System.out.println("2");
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		System.out.println("3");
		// send request and parse result
		ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1103/api/template/"+ template_id + "", HttpMethod.DELETE, entity, String.class);
		System.out.println("4");
		
		
		return new ModelAndView("redirect:/users/profile");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Get metode za komunikaciju sa drugim servisima
	
	public User getUserFromService(HttpServletRequest request, String token, Integer user_id)
	{
		
		System.out.println("1");
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token2 = "Bearer " + token;
		headers.add("X-Authorization", token2);
		headers.add("Content-Type", "application/json");
		
		System.out.println("2");
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		System.out.println("3");
		// send request and parse result
		//ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1102/api/payments/1", HttpMethod.GET, entity, String.class);
		ResponseEntity<String> loginResponseUser = restTemplate.exchange("http://localhost:1105/api/user/" + user_id + "/account", HttpMethod.GET, entity, String.class);
		//String response = loginResponse.getBody().substring(13, loginResponse.getBody().indexOf("totalElements")-3);
		System.out.println("4");
		
		User user = null;
		
		try {
			user = new ObjectMapper().readValue(loginResponseUser.getBody(), User.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("5");
		
		return user;
		
	}
	
	public BankAccount getUserFromService(HttpServletRequest request, String token, String bank_account)
	{
		
		System.out.println("1");
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token2 = "Bearer " + token;
		headers.add("X-Authorization", token2);
		headers.add("Content-Type", "application/json");
		
		System.out.println("2");
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		System.out.println("3");
		// send request and parse result
		//ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1102/api/payments/1", HttpMethod.GET, entity, String.class);
		ResponseEntity<String> loginResponseUser = restTemplate.exchange("http://localhost:1105/api/user/", HttpMethod.GET, entity, String.class);
		ResponseEntity<String> loginResponseBankAccounts = restTemplate.exchange("http://localhost:1101/api/accounts/", HttpMethod.GET, entity, String.class);
		
		//String response = loginResponse.getBody().substring(13, loginResponse.getBody().indexOf("totalElements")-3);
		System.out.println("4");
		
		BankAccount bankAccount = null;
		List<User> users = new ArrayList<User>();
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		
		try {
			bankAccounts = new ObjectMapper().readValue(loginResponseBankAccounts.getBody(), TypeFactory.defaultInstance().constructCollectionType(List.class, BankAccount.class));
			users = new ObjectMapper().readValue(loginResponseUser.getBody(), TypeFactory.defaultInstance().constructCollectionType(List.class, User.class));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("4.1");
		
		for(User item: users)
		{
			System.out.println("Provjera usera " + item.getId());
			List<BankAccount> user_bankAccounts = new ArrayList<BankAccount>();
			
			for(BankAccount item2: bankAccounts)
			{
				System.out.println("Provjera bank racuna " + item2.getBank_account_id());
				if(item.getId().equals(item2.getUser()))
				{
					System.out.println("Dodajem bank racun " + item2.getBank_account_id());
					user_bankAccounts.add(item2);
				}
			}
			
			for(BankAccount item3: user_bankAccounts)
			{
				System.out.println("Provjera racuna da li je to taj sa bank racunom koji je proslijedjen");
				System.out.println("racun " + item3.getBank_account_number() + " = " + bank_account + " ");
				if(item3.getBank_account_number().equals(bank_account)) bankAccount = item3;
			}
		}
		
		System.out.println("5");
		
		return bankAccount;
		
	}
	
	public List<BankAccount> getBankAccountsFromService(HttpServletRequest request, String token, Integer user_id)
	{
		System.out.println("Dobavljanje bank acoounta");
		System.out.println("1");
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token2 = "Bearer " + token;
		headers.add("X-Authorization", token2);
		headers.add("Content-Type", "application/json");
		
		System.out.println("2");
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		System.out.println("3");
		// send request and parse result
		//ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1102/api/payments/1", HttpMethod.GET, entity, String.class);
		ResponseEntity<String> loginResponseUser = restTemplate.exchange("http://localhost:1101/api/accounts", HttpMethod.GET, entity, String.class);
		//String response = loginResponse.getBody().substring(13, loginResponse.getBody().indexOf("totalElements")-3);
		System.out.println("4");
		
		User user = null;
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		List<BankAccount> bankAccounts_filtered = new ArrayList<BankAccount>();
		
		try {
			
			bankAccounts = new ObjectMapper().readValue(loginResponseUser.getBody(), TypeFactory.defaultInstance().constructCollectionType(List.class, BankAccount.class));
			//bankAccounts = (List<BankAccount>) new ObjectMapper().readValue(loginResponseUser.getBody(), BankAccount.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("5");
		
		for(BankAccount item: bankAccounts)
		{
			if(user_id.equals(item.getUser())) bankAccounts_filtered.add(item);
		}
		
		System.out.println("6");
		System.out.println("length:" +  bankAccounts_filtered.size());
		
		return bankAccounts_filtered;
		
	}
	
	public List<Template> getUserTemplatesFromService(HttpServletRequest request, String token, Integer user_id)
	{
		System.out.println("Dobavljanje templejta");
		System.out.println("1");
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token2 = "Bearer " + token;
		headers.add("X-Authorization", token2);
		headers.add("Content-Type", "application/json");
		
		System.out.println("2");
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		System.out.println("3");
		// send request and parse result
		//ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1102/api/payments/1", HttpMethod.GET, entity, String.class);
		ResponseEntity<String> loginResponseUser = restTemplate.exchange("http://localhost:1103/api/template", HttpMethod.GET, entity, String.class);
		//String response = loginResponse.getBody().substring(13, loginResponse.getBody().indexOf("totalElements")-3);
		System.out.println("4");
		
		User user = null;
		List<Template> templates = new ArrayList<Template>();
		List<Template> templates_filtered = new ArrayList<Template>();
		
		try {
			
			templates = new ObjectMapper().readValue(loginResponseUser.getBody(), TypeFactory.defaultInstance().constructCollectionType(List.class, Template.class));
			//bankAccounts = (List<BankAccount>) new ObjectMapper().readValue(loginResponseUser.getBody(), BankAccount.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("5");
		
		for(Template item: templates)
		{
			if(user_id.equals(item.getUser_id())) templates_filtered.add(item);
		}
		
		System.out.println("6");
		System.out.println("length:" +  templates_filtered.size());
		
		return templates_filtered;
		
	}
	
	
	public List<Transaction> getUserTransactionFromService(HttpServletRequest request, String token, Integer user_id)
	{
		System.out.println("Dobavljanje transakcija");
		System.out.println("1");
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token2 = "Bearer " + token;
		headers.add("X-Authorization", token2);
		headers.add("Content-Type", "application/json");
		
		System.out.println("2");
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		System.out.println("3");
		// send request and parse result
		//ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1102/api/payments/1", HttpMethod.GET, entity, String.class);
		ResponseEntity<String> loginResponseUser = restTemplate.exchange("http://localhost:1104/api/transaction", HttpMethod.GET, entity, String.class);
		//String response = loginResponse.getBody().substring(13, loginResponse.getBody().indexOf("totalElements")-3);
		System.out.println("4");
		
		User user = null;
		List<Transaction> transactions = new ArrayList<Transaction>();
		List<Transaction> transactions_filtered = new ArrayList<Transaction>();
		
		try {
			
			transactions = new ObjectMapper().readValue(loginResponseUser.getBody(), TypeFactory.defaultInstance().constructCollectionType(List.class, Transaction.class));
			//bankAccounts = (List<BankAccount>) new ObjectMapper().readValue(loginResponseUser.getBody(), BankAccount.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("5");
		
		for(Transaction item: transactions)
		{
			if(user_id.equals(item.getUser_id())) transactions_filtered.add(item);
		}
		
		System.out.println("6");
		System.out.println("length:" +  transactions_filtered.size());
		
		return transactions_filtered;
		
	}
	
	public List<PaymentModel> getUserPaymentsFromService(HttpServletRequest request, String token, Integer user_id)
	{
		System.out.println("Dobavljanje pajmenta");
		System.out.println("1");
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token2 = "Bearer " + token;
		headers.add("X-Authorization", token2);
		headers.add("Content-Type", "application/json");
		
		System.out.println("2");
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		System.out.println("3");
		// send request and parse result
		//ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1102/api/payments/1", HttpMethod.GET, entity, String.class);
		ResponseEntity<String> loginResponseUser = restTemplate.exchange("http://localhost:1102/api/payments", HttpMethod.GET, entity, String.class);
		System.out.println("response: " + loginResponseUser);
		String response = loginResponseUser.getBody().substring(11, loginResponseUser.getBody().indexOf("last")-2);
		System.out.println("response parsiran: " + response);
		System.out.println("4");
		
		User user = null;
		List<PaymentModel> payments = new ArrayList<PaymentModel>();
		List<PaymentModel> payments_filtered = new ArrayList<PaymentModel>();
		
		try {
			
			payments = new ObjectMapper().readValue(response, TypeFactory.defaultInstance().constructCollectionType(List.class, PaymentModel.class));
			//bankAccounts = (List<BankAccount>) new ObjectMapper().readValue(loginResponseUser.getBody(), BankAccount.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("5");
		
		for(PaymentModel item: payments)
		{
			if(user_id.equals(item.getUserId())) payments_filtered.add(item);
		}
		
		System.out.println("6");
		System.out.println("length:" +  payments_filtered.size());
		
		return payments_filtered;
		
	}
	
	
	
	
	
	
	
	
	
	
	//POST metode za komunikaciju sa drugim servisima
	public boolean setUserBankAccountToService(HttpServletRequest http_request, String token, Integer user_id, BankAccount bankAccount)
	{
		boolean zavrseno = false;
		try 
		{
			System.out.println("Spremanje transakcije");
			System.out.println("1");
			
			RestTemplate restTemplate = new RestTemplate();
			System.out.println("2");
			
			//Request body
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(bankAccount);
			System.out.println("3");
			System.out.println("String: " + json);
			
			//Set headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String token2 = "Bearer " + token;
			headers.add("X-Authorization", token2);
			headers.add("Content-Type", "application/json");
			System.out.println("4");
			
			//Set Http entity
			HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);
			System.out.println("5");
			
			//Send request and parse result
			ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1101/api/accounts", HttpMethod.POST, entity, String.class);
			System.out.println("6");
			
			if(loginResponse.getStatusCode() == HttpStatus.OK)
			{
				System.out.println("6.1");
				zavrseno = true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return zavrseno;
	}
	
	public boolean setUserTemplatesToService(HttpServletRequest http_request, String token, Integer user_id, Template template)
	{
		boolean zavrseno = false;
		try 
		{
			System.out.println("Spremanje templejta");
			System.out.println("1");
			
			RestTemplate restTemplate = new RestTemplate();
			System.out.println("2");
			
			//Request body
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(template);
			System.out.println("3");
			System.out.println("String: " + json);
			
			//Set headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String token2 = "Bearer " + token;
			headers.add("X-Authorization", token2);
			headers.add("Content-Type", "application/json");
			System.out.println("4");
			
			//Set Http entity
			HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);
			System.out.println("5");
			
			//Send request and parse result
			ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1103/api/template/", HttpMethod.POST, entity, String.class);
			System.out.println("6");
			
			if(loginResponse.getStatusCode() == HttpStatus.OK)
			{
				System.out.println("6.1");
				zavrseno = true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return zavrseno;
	}
	
	public boolean setUserTransactionToService(HttpServletRequest http_request, String token, Integer user_id, Transaction transaction)
	{
		boolean zavrseno = false;
		try 
		{
			System.out.println("Spremanje transakcije");
			System.out.println("1");
			
			RestTemplate restTemplate = new RestTemplate();
			System.out.println("2");
			
			//Request body
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(transaction);
			System.out.println("3");
			System.out.println("String: " + json);
			
			//Set headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String token2 = "Bearer " + token;
			headers.add("X-Authorization", token2);
			headers.add("Content-Type", "application/json");
			System.out.println("4");
			
			//Set Http entity
			HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);
			System.out.println("5");
			
			//Send request and parse result
			ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1104/api/transaction/", HttpMethod.POST, entity, String.class);
			System.out.println("6");
			
			if(loginResponse.getStatusCode() == HttpStatus.OK)
			{
				System.out.println("6.1");
				zavrseno = true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return zavrseno;
	}
	
	public boolean setUserPaymentToService(HttpServletRequest http_request, String token, Integer user_id, PaymentModel payment)
	{
		boolean zavrseno = false;
		try 
		{
			System.out.println("Spremanje pajmenta");
			System.out.println("1");
			
			RestTemplate restTemplate = new RestTemplate();
			System.out.println("2");
			
			//Request body
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(payment);
			System.out.println("3");
			System.out.println("String: " + json);
			
			//Set headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String token2 = "Bearer " + token;
			headers.add("X-Authorization", token2);
			headers.add("Content-Type", "application/json");
			System.out.println("4");
			
			//Set Http entity
			HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);
			System.out.println("5");
			
			//Send request and parse result
			ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1102/api/payments", HttpMethod.POST, entity, String.class);
			System.out.println("6");
			
			if(loginResponse.getStatusCode() == HttpStatus.OK)
			{
				System.out.println("6.1");
				zavrseno = true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return zavrseno;
	}
	
	public boolean deleteBankAccount(HttpServletRequest request, String token, BankAccount bankAccount)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String token2 = "Bearer " + token;
		headers.add("X-Authorization", token2);
		headers.add("Content-Type", "application/json");
		
		System.out.println("2");
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		System.out.println("3");
		// send request and parse result
		ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1101/api/accounts/"+ bankAccount.getBank_account_id() + "", HttpMethod.DELETE, entity, String.class);
		System.out.println("4");
		
		return true;
		
	}

	
	
}
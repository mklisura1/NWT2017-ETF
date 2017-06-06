package nwt.ebanking.controller;

import java.awt.image.BandCombineOp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	public ModelAndView userAddBankAccount(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("userprofileview");
		/*
		modelAndView.addObject("user", userService.getUser(user.getUser_id()));
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
	 * GET metoda za dobavljanje transakcija usera 
	 */
	@RequestMapping(value = "/list/transaction", method = RequestMethod.GET)
	public ModelAndView userTransactions(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("usertransactionlistview");
		/*
		modelAndView.addObject("user", userService.getUser(user.getUser_id()));
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
	 * GET metoda za dodavanje jedne transakcije useru 
	 */
	@RequestMapping(value = "/add/transaction", method = RequestMethod.GET)
	public ModelAndView userAddTransaction(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("usertransactionddview");
		/*
		modelAndView.addObject("user", userService.getUser(user.getUser_id()));
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
	 * POST metoda za dodavanje jedne transakcije useru 
	 */
	@RequestMapping(value = "/add/transaction", method = RequestMethod.POST)
	public ModelAndView userAddTransaction(@ModelAttribute("transaction") Transaction transaction, BindingResult result, HttpServletRequest request) {

		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("userprofileview");
		/*
		modelAndView.addObject("user", userService.getUser(user.getUser_id()));
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
	 * GET metoda za dodavanje jednog paymenta useru 
	 */
	@RequestMapping(value = "/add/payment", method = RequestMethod.GET)
	public ModelAndView userAddPayment(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("userpaymentaddview");
		/*
		modelAndView.addObject("user", userService.getUser(user.getUser_id()));
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
	 * POST metoda za dodavanje jednog bankovnog racuna useru 
	 */
	/*
	 * Mora se promijeniti ModelAtribut jer nema trenutno klase Payment u modelima
	 */
	@RequestMapping(value = "/add/payment", method = RequestMethod.POST)
	public ModelAndView userAddPayment(@ModelAttribute("payment") Transaction payment, BindingResult result, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("userprofileview");
		/*
		modelAndView.addObject("user", userService.getUser(user.getUser_id()));
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
	 * GET metoda za dobavljanje templejta usera 
	 */
	@RequestMapping(value = "/list/templates", method = RequestMethod.GET)
	public ModelAndView userTemplates(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("usertemplatelistview");
		/*
		modelAndView.addObject("user", userService.getUser(user.getUser_id()));
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
	 * GET metoda za dodavanje jednog templejta useru 
	 */
	@RequestMapping(value = "/add/template", method = RequestMethod.GET)
	public ModelAndView userAddTemplate(HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("usertemplateaddview");
		/*
		modelAndView.addObject("user", userService.getUser(user.getUser_id()));
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
	 * POST metoda za dodavanje jednog templejta useru 
	 */
	@RequestMapping(value = "/add/temoplate", method = RequestMethod.POST)
	public ModelAndView userAddTemplate(@ModelAttribute("template") Template template, BindingResult result, HttpServletRequest request) {
		
		if (request.getSession().getAttribute("user") == null) {
			return new ModelAndView("login");
		}
		Integer user_id = Integer.parseInt((String) request.getSession().getAttribute("user"));
		
		ModelAndView modelAndView = new ModelAndView("userprofileview");
		/*
		modelAndView.addObject("user", userService.getUser(user.getUser_id()));
		modelAndView.addObject("userDetails", user.getUserDetails());
		modelAndView.addObject("friends_size", user.getFriends().size());
		
		List<GalleryPicture> pictures = new ArrayList<GalleryPicture>();
		for(Gallery item: user.getGalleries()) pictures.addAll(item.getGalleryPictures());
		modelAndView.addObject("pictures", pictures);
		modelAndView.addObject("photos_size", pictures.size());
		*/
		return modelAndView;
	}
	
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
	
	
}
package nwt.ebanking.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	
	
	// Logiranje korisnika na sistem
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request) {
		//User user = new User();
		
		return new ModelAndView("login");
	}

	/*
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest http_request, Map<String, Object> model) 
	{
		RestTemplate restTemplate = new RestTemplate();

		// create request body
		JSONObject request = new JSONObject();
		request.put("username", "svlada@gmail.com");
		request.put("password", "test1234");

		// set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("X-Requested-With", "XMLHttpRequest");
		headers.add("Content-Type", "application/json");
		
		// set Http entity
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

		// send request and parse result
		ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1105/api/auth/login", HttpMethod.POST, entity, String.class);
		if (loginResponse.getStatusCode() == HttpStatus.OK) 
		{
			model.put("message", loginResponse.getBody());
		} 
		else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) 
		{
			model.put("message", loginResponse.getBody());
			// nono... bad credentials
		}
		
		
		
		HttpSession http_session = http_request.getSession(true);
		
		http_session.setAttribute("token", loginResponse.getBody());					
		// limit trajanja sesije na 3 sata
		http_session.setMaxInactiveInterval(10800);
		
		
		return new ModelAndView("welcome");
	}
	*/
	
	
	
	//Login na sistem
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ModelAndView home(HttpServletRequest http_request, @RequestParam("username") String username,
			@RequestParam("password") String password) throws ServletException, IOException 
	{
		//Provjera da li je vec logovan korisnik, ako jeste nema sta ponovo unositi
		String session_token = (String) http_request.getSession().getAttribute("token");
		if(session_token != null && !session_token.isEmpty())
		{
			ModelAndView modelAndView = new ModelAndView("redirect:/welcome");
			
			return modelAndView;
		}
		
		//Ako korisnik nije logovan onda mora unijeti username i password
		ModelAndView modelAndView = null;

		try {
			
			RestTemplate restTemplate = new RestTemplate();

			//Request body
			JSONObject request = new JSONObject();
			request.put("username", username);
			request.put("password", password);

			//Set headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("X-Requested-With", "XMLHttpRequest");
			headers.add("Content-Type", "application/json");
			
			//Set Http entity
			HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

			//Send request and parse result
			ResponseEntity<String> loginResponse = restTemplate.exchange("http://localhost:1105/api/auth/login", HttpMethod.POST, entity, String.class);
			System.out.println("Dobio sam odgovor");
			System.out.println("Status kod: " + loginResponse.getStatusCode());
			System.out.println("Status kod: " + loginResponse.getBody());
			
			if (loginResponse.getStatusCode() == HttpStatus.OK) 
			{
				
				//Kreiranje sesije za korisnika nakon uspjesnog logiranja
				HttpSession http_session = http_request.getSession(true);
				
				//Spremanje tokena u sesiju
				String token = loginResponse.getBody().substring(23, loginResponse.getBody().toString().indexOf("refreshToken")-3);
				String refreshToken = loginResponse.getBody().substring(loginResponse.getBody().toString().indexOf("refreshToken")+15, loginResponse.getBody().toString().lastIndexOf('"'));
				
				http_session.setAttribute("token", token);
				http_session.setAttribute("refreshToken", refreshToken);
				
				//Limit trajanja sesije na 3 sata
				http_session.setMaxInactiveInterval(10800);
				
				modelAndView = new ModelAndView("redirect:/welcome");
				//User user = userService.getUserByUsername(username);
				//modelAndView.addObject("user", user);
			}
			else
			{
				modelAndView = new ModelAndView("login");
				modelAndView.addObject("error", " * The username or password you entered is incorrect!");
			}
			
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
			modelAndView = new ModelAndView("login");
		}
		 
		return modelAndView;
	}

	// Odjava sa sistema
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage(HttpServletRequest request) {

		HttpSession http_session = request.getSession(true);
		http_session.invalidate();

		return new ModelAndView("login");
	}

}
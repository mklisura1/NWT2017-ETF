package nwt.ebanking.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.client.RestTemplate;

@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}
	
	@RequestMapping("/welcome")
	public String welcome2(HttpServletRequest http_request, Map<String, Object> model) {
		
		String token = (String) http_request.getSession().getAttribute("token");
		String refreshToken = (String) http_request.getSession().getAttribute("refreshToken");
		
		model.put("message", token);
		
		
		System.out.println("toke: " + token);
		System.out.println("refresh token: " + refreshToken);
		
		return "welcome";
	}
/*
	@RequestMapping("/login/")
	public String login(Map<String, Object> model) 
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
		
		return "welcome";
	}
*/
}
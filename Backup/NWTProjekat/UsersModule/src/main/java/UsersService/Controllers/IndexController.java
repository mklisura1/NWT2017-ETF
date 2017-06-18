package UsersService.Controllers;


import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import UsersService.Models.User;
import UsersService.Models.UserRole;
import UsersService.Security.Auth.JwtAuthenticationToken;
import UsersService.Security.Model.UserContext;



@Controller
public class IndexController {

	@RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Home page of Users Microservice";
    }
	
	@RequestMapping(value="/api/me", method=RequestMethod.GET)
    public @ResponseBody UserContext get(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }
	
	//Samo za ROLE_MEMBER
	@RequestMapping(value="/api/he", method=RequestMethod.GET)
    public @ResponseBody ResponseEntity<User> getAnother(JwtAuthenticationToken token) {
		String vrati = ", details: " + token.getDetails() ;
		
		Collection<GrantedAuthority> roles = token.getAuthorities();
		
		for(GrantedAuthority ga : roles)
		{
			System.out.println(ga.getAuthority());
			
			if(ga.getAuthority().toString().equals("ROLE_MEMBER"))
			{
				return new ResponseEntity<User>(HttpStatus.CREATED);	
			}
		}
		return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);	
    }
}
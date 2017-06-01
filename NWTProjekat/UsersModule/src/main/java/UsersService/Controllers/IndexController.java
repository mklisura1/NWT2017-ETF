package UsersService.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import UsersService.Models.UserRole;
//import UsersService.Security.Auth.JwtAuthenticationToken;
//import UsersService.Security.Model.UserContext;



@Controller
public class IndexController {

	@RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Home page of Users Microservice";
    }
	
//	@RequestMapping(value="/api/me", method=RequestMethod.GET)
//    public @ResponseBody UserContext get(JwtAuthenticationToken token) {
//        return (UserContext) token.getPrincipal();
//    }
//
//	//Samo za ROLE_MEMBER
//	@RequestMapping(value="/api/he", method=RequestMethod.GET)
//    public @ResponseBody ResponseEntity<User> getAnother(JwtAuthenticationToken token) {
//		String vrati = ", details: " + token.getDetails() ;
//
//		Collection<GrantedAuthority> roles = token.getAuthorities();
//
//		for(GrantedAuthority ga : roles)
//		{
//			System.out.println(ga.getAuthority());
//
//			if(ga.getAuthority().toString().equals("ROLE_MEMBER"))
//			{
//				return new ResponseEntity<User>(HttpStatus.CREATED);
//			}
//		}
//		return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
//    }
}
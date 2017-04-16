package UsersService.Controllers;

import UsersService.Models.User;
import UsersService.Services.UserService;
import UsersService.Templates.PaymentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping(value="/api")
public class UserController 
{
	@Autowired
	private UserService userService;


	//-------------------Retrieve All Users--------------------------------------------------------
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = (List<User>) userService.listAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        System.out.println("Fetching User with id " + id);
        User user = userService.getUserById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        //Komunikacija sa Payments servisom
        List<PaymentModel> payments = userService.getPayments(id);
        user.setUserPayments(payments);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getFirst_name());
 
        if (userService.isUserExist(user)) {
            System.out.println("A User with id " + user.getUser_id() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        
        if (userService.isUserExistByUsername(user.getUser_name())) {
            System.out.println("A User with user name " + user.getUser_name() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        
        userService.saveUser(user);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getUser_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        System.out.println("Updating User " + id);
         
        User currentUser = userService.getUserById(id);
         
        if (currentUser==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
 
        currentUser.setFirst_name(user.getFirst_name());
        currentUser.setLast_name(user.getLast_name());
         
        userService.updateUser(currentUser);;
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }
 
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        User user = userService.getUserById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
 
     
    //------------------- Delete All Users --------------------------------------------------------
    
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");
 
        for(User item: userService.listAllUsers())
        {
        	userService.deleteUser(item.getUser_id());
        }
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
    
    //------------------- Generate password salt and hash for User --------------------------------------------------------
    
    @RequestMapping(value = "/user/{id}/generate/{user_name}/{password}", method = RequestMethod.POST)
    public ResponseEntity<Void> registerUser(@PathVariable("id") Integer id, @PathVariable("user_name") String user_name, @PathVariable("password") String password) { 
    	
    	User existing_user = userService.getUserById(id);
    	
    	//Generisanje salta i hasha za password
    	String user_password_salt = null;
    	String user_password_hash = null;
    	try
    	{
    		user_password_salt = userService.getSalt();
    		user_password_hash = userService.generatePasswordHash(password);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		
		existing_user.setUser_password_salt(user_password_salt);
		existing_user.setUser_password_hash(user_password_hash);
 
		userService.updateUser(existing_user);
 
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    
    //------------------- Check username and password for login User --------------------------------------------------------
    
    @RequestMapping(value = "/user/check/{user_name}/{password}", method = RequestMethod.GET)
    public ResponseEntity<Void> checkUsernameAndPassword(@PathVariable("user_name") String user_name, @PathVariable("password") String password) { 

    	boolean sve_uredu = false;
    	//provjeri da li postoji user sa ovim username
    	if(userService.isUserExistByUsername(user_name))
    	{
    		//Ako postoji onda mozemo dobaviti usera preko username te provjeriti password
    		User user = userService.getUserByUsername(user_name);
    		
    		try {
    			//Provjera da li je sve uredu sa passwordom
				if (userService.validatePassword(password, user.getUser_password_hash())) 
				{
					sve_uredu=true;
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
    	}
    	
    	//Provjera da li je sve uredu sa password i user_name
    	if(sve_uredu)
    	{
    		return new ResponseEntity<Void>(HttpStatus.OK);
    	}
    	else 
    	{
    		return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
    	}
    }
}

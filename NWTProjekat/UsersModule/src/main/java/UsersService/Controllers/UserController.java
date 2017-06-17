package UsersService.Controllers;

import UsersService.Models.User;
import UsersService.Services.UserService;
import UsersService.Templates.BankAccount;
import UsersService.Templates.PaymentModel;
import UsersService.Templates.Template;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java

@Controller
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DiscoveryClient discoveryClient;


    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = (List<User>) userService.listAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    //-------------------Retrieve Single User--------------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Integer id) {
        System.out.println("Fetching User with id " + id);


        //Long _id = Long.valueOf(id);

        //Long _id = id.longValue();


        User user = userService.getUserById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Fetched User with id " + id);

        //Komunikacija sa Payments servisom
        List<PaymentModel> payments = userService.getPayments(id);

        //Komunikacija sa BankAccounts servisom
        List<BankAccount> userBankAccounts = userService.getUserBankAccounts(id);

        user.setUserPayments(payments);
        user.setUserBankAccounts(userBankAccounts);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //-------------------Retrieve Single User--------------------------------------------------------

    @RequestMapping(value = "/user/{id}/account", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getBankAccountOwnerByUserId(@PathVariable("id") Integer id) {
        System.out.println("Fetching User with id " + id);

        //Long _id = new Long(id);

        User user = userService.getUserById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //-------------------Retrieve User Templates by UserId-------------------------------------------

    @RequestMapping(value = "/user/{id}/template", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Template>> getTemplatesByUserId(@PathVariable("id") Integer id) {

        //Long _id = new Long(id);

        User user = userService.getUserById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<List<Template>>(HttpStatus.NOT_FOUND);
        }

        String url = discoveryClient.getInstances("templates").get(0).getUri().toString();
        url += "/api/user/" + id + "/template";

        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        /*HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    //HttpEntity<?> entity = new HttpEntity<Object>(,headers);*/
        ResponseEntity<ArrayList> response = restTemplate.getForEntity(url, ArrayList.class);
        //List<Transaction> transactions = (List<Transaction>) responseEntity.getBody();

        if (HttpStatus.OK != response.getStatusCode()) {
            System.out.println("Templates not found!");

            return new ResponseEntity<List<Template>>(HttpStatus.NOT_FOUND);

        }

        List<Template> templates = response.getBody();

        System.out.println(templates);

        return new ResponseEntity<List<Template>>(templates, HttpStatus.OK);

    }


    //-------------------Create a User--------------------------------------------------------

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getFirst_name());

//        if (userService.isUserExist(user)) {
//            System.out.println("A User with id " + user.getId() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }

        if (userService.isUserExistByUsername(user.getUsername())) {
            System.out.println("A User with user name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        user.setPassword(generatePassword(user.getPassword()));


        //dodati jos rolu  ali kako ???

        userService.saveUser(user);



        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    private String generatePassword(String nonHashedPassword) {
        String salt = BCrypt.gensalt(10);
        return BCrypt.hashpw(nonHashedPassword, salt);
    }


    //------------------- Update a User --------------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        System.out.println("Updating User " + id);

        //Long _id = new Long(id);

        User currentUser = userService.getUserById(id);

        if (currentUser == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currentUser.setAddress(user.getAddress());
        currentUser.setJmbg(user.getJmbg());
        currentUser.setEmail(user.getEmail());
        currentUser.setBirth_date(user.getBirth_date());
        currentUser.setFirst_name(user.getFirst_name());
        currentUser.setLast_name(user.getLast_name());
        if(user.getPassword() != null && user.getPassword().length() > 0)
            currentUser.setPassword(generatePassword(user.getPassword()));
        currentUser.setUsername(user.getUsername());
        currentUser.setMobile(user.getMobile());
        userService.updateUser(currentUser);

        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
    }

    //------------------- Delete a User --------------------------------------------------------

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting User with id " + id);

        //Long _id = new Long(id);

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
        /*
        for(User item: userService.listAllUsers())
        {
        	userService.deleteUser(item.getId());
        }
        */
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    //------------------- Reset User password via mail--------------------------------------------------------

    @RequestMapping(value = "/user/{username}/resetPassword", method = RequestMethod.POST)
    public ResponseEntity<Void> resetPassword(@PathVariable("username") String username) {
        System.out.println("Reset password");

        if (userService.isUserExistByUsername(username)) {
            User user = userService.getUserByUsername(username);
            generateNewPassword(user);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    private void generateNewPassword(User user) {
        //Generisanje salta i hasha za password

        try {
            String randomString = RandomStringUtils.randomAlphanumeric(5).toUpperCase();

            String salt = BCrypt.gensalt(10);
            String hashed_password = BCrypt.hashpw(randomString, salt);
            user.setPassword(hashed_password);
            userService.saveUser(user);
            //posalji poruku na email sa novim randomStringom koji je ujedno i novi password
            SendSimpleMessage(user.getUsername(), user.getEmail(), randomString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ClientResponse SendSimpleMessage(String name, String mail, String password) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "key-71b0342222767c9f79abcfcba8ec6e26"));
        com.sun.jersey.api.client.WebResource webResource = client.resource("https://api.mailgun.net/v3/sandbox2fe5192718eb48ed97ec1436fa8b0566.mailgun.org/messages");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "NWT Banking <postmaster@sandbox2fe5192718eb48ed97ec1436fa8b0566.mailgun.org>");
        formData.add("to", name +" <"+mail+">");
        formData.add("subject", "NWT Banking - Password reset for " + name);
        formData.add("text", "Your new password is: " + password);
        return webResource.type(String.valueOf(MediaType.APPLICATION_FORM_URLENCODED)).
                post(ClientResponse.class, formData);
    }
    //------------------- Generate password salt and hash for User --------------------------------------------------------
/*    
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
*/
}

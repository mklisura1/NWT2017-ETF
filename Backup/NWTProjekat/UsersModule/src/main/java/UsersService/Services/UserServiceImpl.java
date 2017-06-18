package UsersService.Services;

import UsersService.Models.User;
import UsersService.Repository.UserRepository;
import UsersService.Templates.BankAccount;
import UsersService.Templates.PaymentModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	private Object object;

	@Autowired
	private LoadBalancerClient loadBalancer;

	private RestTemplate restTemplate = new RestTemplate();
	
	@Override
    public Optional<User> getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

	@Override
    public Iterable<User> listAllUsers() 
    {
        return userRepository.findAll();
    }
    
    public boolean isUserExist(User user) 
    {
    	return true;//userRepository.exists(user.getId());
    }
    
    public boolean isUserExistByUsername(String user_name)
    {
    	boolean postoji = false;
    	List<User> users = (List<User>) userRepository.findAll();
    	
    	for(User item : users)
    	{
    		if(item.getUsername().equals(user_name)) 
    		{
    			postoji = true;
    			break;
    		}
    	}
    	return postoji;
    }

    @Override
    public User getUserById(Integer id) 
    {
    	//Integer _id = (int)(long) id;
        return userRepository.findOne(id);
    }
    
    public User getUserByUsername(String user_name)
    {
    	User user = null;
    	
    	List<User> users = (List<User>) userRepository.findAll();
    	
    	for(User item : users)
    	{
    		if(item.getUsername().equals(user_name)) 
    		{
    			user = item;
    			break;
    		}
    	}
    	
    	return user;
    }

    public User saveUser(User User) 
    {
        return userRepository.save(User);
    }
    
    public void updateUser(User user)
    {     
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) 
    {
    	//Integer _id = (int)(long) id;
        userRepository.delete(id);
    }
    
    @Override
	public String getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt.toString();
	}
    
    @Override
	public String generatePasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt().getBytes();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}
    
	@Override
	public String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	@Override
	public boolean validatePassword(String inputPassword, String hashedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = hashedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);

		PBEKeySpec spec = new PBEKeySpec(inputPassword.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;
		for (int i = 0; i < hash.length && i < testHash.length; i++) {
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	@Override
	public byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	//Metode od drugih mikroservisa
	@Override
	public List<PaymentModel> getPayments(Integer id){

		//Pronalazenje mikroservisa po njegovom nazivu - onako kako ga Eureka vidi
		ServiceInstance instance = loadBalancer.choose("payments");

		LOG.info("URL: " + instance.getUri());
		String url = instance.getUri() + "/api/payments?userId={id}";
		LOG.info("GET Payments from URL: {}", url);

		ResponseEntity<Object>  response = restTemplate.getForEntity(url, Object.class, id);
		LOG.info("Response: ", response);
		LOG.info("Response: ", response.getBody());
		LinkedHashMap<String, String> objects = (LinkedHashMap<String, String>) response.getBody();
		List<PaymentModel> paymentModelList = (List<PaymentModel>) (Object) objects.get("content");
		LOG.info("Responseeee: {}", objects.get("content") );
		return paymentModelList;
	}

	@Override
	public List<BankAccount> getUserBankAccounts(Integer user_id) {

		//Pronalazenje mikroservisa po njegovom nazivu - onako kako ga Eureka vidi
		ServiceInstance instance = loadBalancer.choose("bankaccounts");
		String url = instance.getUri() + "/api/accounts";

		//ResponseEntity<Object>  response = restTemplate.getForEntity(url, Object.class);
		ResponseEntity<List<BankAccount>> bankAccounts =
		        restTemplate.exchange(url,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<BankAccount>>() {
		            });
		
		
		List<BankAccount> bankAccountsResponse = bankAccounts.getBody();
		List<BankAccount> bankAccountsFiltered = new ArrayList<BankAccount>();
		
		for(BankAccount item: bankAccountsResponse)
		{
			if(item.getUser() == user_id)
			{
				bankAccountsFiltered.add(item);
			}
		}
		
		return bankAccountsFiltered;
	}
}
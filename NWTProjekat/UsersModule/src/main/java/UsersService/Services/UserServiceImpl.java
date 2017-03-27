package UsersService.Services;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UsersService.Models.User;
import UsersService.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepository userRepository;

    @Override
    public Iterable<User> listAllUsers() 
    {
        return userRepository.findAll();
    }
    
    public boolean isUserExist(User user) 
    {
    	return userRepository.exists(user.getUser_id());
    }
    
    public boolean isUserExistByUsername(String user_name)
    {
    	boolean postoji = false;
    	List<User> users = (List<User>) userRepository.findAll();
    	
    	for(User item : users)
    	{
    		if(item.getUser_name().equals(user_name)) 
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
        return userRepository.findOne(id);
    }
    
    public User getUserByUsername(String user_name)
    {
    	User user = null;
    	
    	List<User> users = (List<User>) userRepository.findAll();
    	
    	for(User item : users)
    	{
    		if(item.getUser_name().equals(user_name)) 
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
}
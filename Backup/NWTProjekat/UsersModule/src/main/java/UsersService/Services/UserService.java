package UsersService.Services;

import UsersService.Models.User;
import UsersService.Templates.BankAccount;
import UsersService.Templates.PaymentModel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;


public interface UserService 
{
	public Optional<User> getByUsername(String username);
	
    Iterable<User> listAllUsers();
    
    boolean isUserExist(User user);
    
    boolean isUserExistByUsername(String user_name);

    User getUserById(Integer id);
    
    User getUserByUsername(String user_name);

    User saveUser(User user);
    
    void updateUser(User user);

    void deleteUser(Integer id);
    
    String getSalt() throws NoSuchAlgorithmException;
    
    String generatePasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
    
    String toHex(byte[] array) throws NoSuchAlgorithmException;
    
    boolean validatePassword(String inputPassword, String hashedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException;
    
    byte[] fromHex(String hex) throws NoSuchAlgorithmException;

    //Metode od drugih mikroservisa
    List<PaymentModel> getPayments(Integer id);
    
    List<BankAccount> getUserBankAccounts(Integer user_id);
}
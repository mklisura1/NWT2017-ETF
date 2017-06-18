package UsersService.Models;

import UsersService.Templates.BankAccount;
import UsersService.Templates.PaymentModel;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="APP_USER")
public class User {
    @Id @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="username")
    private String username;
    
    @Column(name="password")
    private String password;
    
    @OneToMany
    @JoinColumn(name="APP_USER_ID", referencedColumnName="ID")
    private List<UserRole> roles;
	
	
	
	/*
	@Id
	@GeneratedValue
	private Integer user_id;
	private String user_name;	
	private String user_password_salt;
	private String user_password_hash;	
	*/
	private String first_name;
	private String last_name;
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date birth_date;
	private String email;
	private String jmbg;
	private String address;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_type_id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private UserType userType;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_picture_id")
	@Cascade({CascadeType.SAVE_UPDATE})
	private UserPicture userPicture;

	@Transient
	private List<PaymentModel> userPayments;
	
	@Transient
	private List<BankAccount> userBankAccounts;
	
	//Constructors
	public User() {
		super();
	}
/*	
	public User(Integer user_id, String user_name, String user_password_salt, String user_password_hash,
			String first_name, String last_name, Date birth_date, String email, String jmbg, String address,
			UserType userType, UserPicture userPicture) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password_salt = user_password_salt;
		this.user_password_hash = user_password_hash;
		this.first_name = first_name;
		this.last_name = last_name;
		this.birth_date = birth_date;
		this.email = email;
		this.jmbg = jmbg;
		this.address = address;
		this.userType = userType;
		this.userPicture = userPicture;
	}

	//Getters & Setters
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password_salt() {
		return user_password_salt;
	}
	public void setUser_password_salt(String user_password_salt) {
		this.user_password_salt = user_password_salt;
	}
	public String getUser_password_hash() {
		return user_password_hash;
	}
	public void setUser_password_hash(String user_password_hash) {
		this.user_password_hash = user_password_hash;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public UserPicture getUserPicture() {
		return userPicture;
	}
	public void setUserPicture(UserPicture userPicture) {
		this.userPicture = userPicture;
	}
	public List<PaymentModel> getUserPayments() {
		return userPayments;
	}
	public void setUserPayments(List<PaymentModel> userPayments) {
		this.userPayments = userPayments;
	}
	public List<BankAccount> getUserBankAccounts() {
		return userBankAccounts;
	}
	public void setUserBankAccounts(List<BankAccount> userBankAccounts) {
		this.userBankAccounts = userBankAccounts;
	}
	
*/


	public String getUsername() {
		return username;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserPicture getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(UserPicture userPicture) {
		this.userPicture = userPicture;
	}

	public List<PaymentModel> getUserPayments() {
		return userPayments;
	}

	public void setUserPayments(List<PaymentModel> userPayments) {
		this.userPayments = userPayments;
	}

	public List<BankAccount> getUserBankAccounts() {
		return userBankAccounts;
	}

	public void setUserBankAccounts(List<BankAccount> userBankAccounts) {
		this.userBankAccounts = userBankAccounts;
	}


	public User(/*Integer id,*/ String username, String password, List<UserRole> roles, String first_name, String last_name,
			Date birth_date, String email, String jmbg, String address, UserType userType, UserPicture userPicture,
			List<PaymentModel> userPayments, List<BankAccount> userBankAccounts) {
		super();
		//this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.first_name = first_name;
		this.last_name = last_name;
		this.birth_date = birth_date;
		this.email = email;
		this.jmbg = jmbg;
		this.address = address;
		this.userType = userType;
		this.userPicture = userPicture;
		this.userPayments = userPayments;
		this.userBankAccounts = userBankAccounts;
	}
	
	
	
}

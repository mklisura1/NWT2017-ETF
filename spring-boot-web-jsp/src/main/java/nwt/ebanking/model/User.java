package nwt.ebanking.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


public class User {
	
    private Integer id;
    private String username;
    private String password;
    private List<Object> roles;
	private String first_name;
	private String last_name;
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date birth_date;
	private String email;
	private String jmbg;
	private String address;
	private Object userType;
	private Object userPicture;
	
	//Constructors
	public User() {
		super();
	}
	
	public User(Integer id, String username, String password, List<Object> roles, String first_name, String last_name,
			Date birth_date, String email, String jmbg, String address, Object userType, Object userPicture) {
		super();
		this.id = id;
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
	}
	
	//Getters & Setters
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

	public List<Object> getRoles() {
		return roles;
	}

	public void setRoles(List<Object> roles) {
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

	public Object getUserType() {
		return userType;
	}

	public void setUserType(Object userType) {
		this.userType = userType;
	}

	public Object getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(Object userPicture) {
		this.userPicture = userPicture;
	}
}

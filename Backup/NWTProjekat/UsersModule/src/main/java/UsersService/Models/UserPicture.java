package UsersService.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="user_picture")
public class UserPicture {
	
	@Id
	@GeneratedValue
	private Integer user_picture_id;
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date upload_date;
	private Boolean using_this_picture;
	@Column(name="server_location")
	private String picture_server_location;
	
	//Constructors
	public UserPicture() {
		super();
	}
		
	public UserPicture(Integer user_picture_id, Date upload_date, Boolean using_this_picture,
			String picture_server_location) {
		super();
		this.user_picture_id = user_picture_id;
		this.upload_date = upload_date;
		this.using_this_picture = using_this_picture;
		this.picture_server_location = picture_server_location;
	}

	//Getters & Setters
	public Integer getUser_picture_id() {
		return user_picture_id;
	}
	public void setUser_picture_id(Integer user_picture_id) {
		this.user_picture_id = user_picture_id;
	}
	public Date getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Date upload_date) {
		this.upload_date = upload_date;
	}
	public Boolean getUsing_this_picture() {
		return using_this_picture;
	}
	public void setUsing_this_picture(Boolean using_this_picture) {
		this.using_this_picture = using_this_picture;
	}
	public String getPicture_server_location() {
		return picture_server_location;
	}
	public void setPicture_server_location(String picture_server_location) {
		this.picture_server_location = picture_server_location;
	}	
}

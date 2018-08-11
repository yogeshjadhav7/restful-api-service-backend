package web.db.models.intellecto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.*;

@Entity
@Table(name = "intellecto_users")
public class Intellecto_Users extends Model{
	
	@Id
	@Column(name="`id`")
	private long id;

	@Column(name="`username`")
	private String userName;
	
	@Column(name="`email`")
    private String email;
	
	@Column(name="`phone_number`")
	private String phoneNumber;
	
	@Column(name="`country`")
	private String country;
	
	@Column(name="`verified`")
	private boolean verified;
	
	@Column(name="`device`")
	private String device;
	
    
    public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public boolean isVerified() {
		return verified;
	}



	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	


	public String getDevice() {
		return device;
	}



	public void setDevice(String device) {
		this.device = device;
	}



	public static Finder<Long, Intellecto_Users> getFind() {
		return find;
	}



	public static void setFind(Finder<Long, Intellecto_Users> find) {
		Intellecto_Users.find = find;
	}



	public static Finder<Long, Intellecto_Users> find = new Finder<Long, Intellecto_Users>(Intellecto_Users.class);
    
}